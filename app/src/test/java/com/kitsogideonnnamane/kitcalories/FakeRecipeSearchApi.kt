package com.kitsogideonnnamane.kitcalories

import com.kitsogideonnnamane.kitcalories.data.Image
import com.kitsogideonnnamane.kitcalories.data.Images
import com.kitsogideonnnamane.kitcalories.data.RecipeModel
import com.kitsogideonnnamane.kitcalories.data.Result
import com.kitsogideonnnamane.kitcalories.data.source.Recipe
import com.kitsogideonnnamane.kitcalories.data.source.RecipeResult
import com.kitsogideonnnamane.kitcalories.data.source.RecipeSearchApi
import com.kitsogideonnnamane.kitcalories.data.source.RecipeSearchResult
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class FakeRecipeSearchApi(private val httpClient: OkHttpClient) : RecipeSearchApi {
    private val moshi = Moshi.Builder().build()
    private val searchJsonAdapter = moshi.adapter(RecipeSearchResult::class.java)
    private val recipeJsonAdapter = moshi.adapter(RecipeResult::class.java)

    private fun toRecipeModel(recipe: Recipe): RecipeModel {
        val images_ = recipe.images
        val images = listOf(
            Images(
                LARGE = Image(
                    height = images_.LARGE.height,
                    url = images_.LARGE.url,
                    width = images_.LARGE.width
                ),
                REGULAR = Image(
                    height = images_.REGULAR.height,
                    url = images_.REGULAR.url,
                    width = images_.REGULAR.width
                ),
                SMALL = Image(
                    height = images_.SMALL.height,
                    url = images_.SMALL.url,
                    width = images_.SMALL.width
                ),
                THUMBNAIL = Image(
                    height = images_.SMALL.height,
                    url = images_.SMALL.url,
                    width = images_.SMALL.width
                ),
            )
        )

        return RecipeModel(
            title = recipe.label,
            description = "fake description",
            ingredients = recipe.ingredientLines,
            images = images,
            isFavorite = false,
            dietLabels = recipe.dietLabels,
            healthLabels = recipe.healthLabels,
            cautions = recipe.cautions,
            mealType = recipe.mealType,
            dishType = recipe.dishType,
            cuisineType = recipe.cuisineType,
        )
    }

    override suspend fun searchRecipes(searchQuery: String): Result<List<RecipeModel>> {
        val request = Request.Builder()
            .url("http://localhost:9090/search?q=searchQuery")
            .build()

        httpClient.newCall(request).execute().use { res ->
            if (!res.isSuccessful) return Result.Error(IOException("Unexpected code $res"))
            val rawResponse = searchJsonAdapter.fromJson(res.body!!.source())
            val response = rawResponse?.hits?.map { hit ->
                toRecipeModel(hit.recipe)
            }
            return Result.Success(response!!)
        }
    }

    override suspend fun getRecipe(recipeId: String): Result<RecipeModel> {
        return Result.Success(
            RecipeModel(
                title = "fake title",
                description = "fake description",
                ingredients = listOf("ingredient 1", "ingredient 2"),
                images = listOf<Images>(),
                isFavorite = false,
                dietLabels = listOf("diet"),
                healthLabels = listOf("diet"),
                cautions = listOf("diet"),
                mealType = listOf("diet"),
                dishType = listOf("diet"),
                cuisineType = listOf("diet"),
            )
        )
    }

    override suspend fun nextPage(): Result<List<RecipeModel>> {
        return Result.Success(listOf<RecipeModel>())
    }

    override suspend fun previousPage(): Result<List<RecipeModel>> {
        return Result.Success(listOf<RecipeModel>())
    }
}