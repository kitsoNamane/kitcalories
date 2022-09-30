package com.kitsogideonnnamane.kitcalories

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

    private var nextPageUrl: String? = null
    private var previousPageUrl: String? = null

    private fun toRecipeModel(recipe: Recipe): RecipeModel {
        val images = recipe.images

        return RecipeModel(
            title = recipe.label,
            description = "fake description",
            ingredients = recipe.ingredientLines,
            thumbNailUrl = images.THUMBNAIL?.url,
            largeImageUrl = images.LARGE?.url,
            regularImageUrl = images.REGULAR?.url,
            smallImageUrl = images.SMALL?.url,
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
            .url("http://localhost:9090/search?q=$searchQuery")
            .build()

        httpClient.newCall(request).execute().use { res ->
            if (!res.isSuccessful) return Result.Error(IOException("Unexpected code $res"))
            val rawResponse = searchJsonAdapter.fromJson(res.body!!.source())
            val response = rawResponse?.hits?.map { hit ->
                toRecipeModel(hit.recipe)
            }

            previousPageUrl = request.url.toString()
            nextPageUrl = rawResponse?._links?.next?.href

            return Result.Success(response!!)
        }
    }

    override suspend fun getRecipe(recipeId: String): Result<RecipeModel> {
        val request = Request.Builder()
            .url("http://localhost:9090/recipe?q=$recipeId")
            .build()

        httpClient.newCall(request).execute().use { res ->
            if (!res.isSuccessful) return Result.Error(IOException("Unexpected code $res"))
            val rawResponse = recipeJsonAdapter.fromJson(res.body!!.source())
            return Result.Success(toRecipeModel(rawResponse?.recipe!!))
        }
    }

    override suspend fun nextPage(): Result<List<RecipeModel>> {
        if (nextPageUrl.isNullOrBlank()) return Result.Error(Exception("No more results"))

        val request = Request.Builder()
            .url(nextPageUrl!!)
            .build()

        httpClient.newCall(request).execute().use { res ->
            if (!res.isSuccessful) return Result.Error(IOException("Unexpected code $res"))
            val rawResponse = searchJsonAdapter.fromJson(res.body!!.source())
            val response = rawResponse?.hits?.map { hit ->
                toRecipeModel(hit.recipe)
            }

            previousPageUrl = request.url.toString()
            nextPageUrl = rawResponse?._links?.next?.href

            return Result.Success(response!!)
        }
    }

    override suspend fun previousPage(): Result<List<RecipeModel>> {
        if (previousPageUrl.isNullOrBlank()) return Result.Error(Exception("No previous result"))

        val request = Request.Builder()
            .url(previousPageUrl!!)
            .build()

        httpClient.newCall(request).execute().use { res ->
            if (!res.isSuccessful) return Result.Error(IOException("Unexpected code $res"))
            val rawResponse = searchJsonAdapter.fromJson(res.body!!.source())
            val response = rawResponse?.hits?.map { hit ->
                toRecipeModel(hit.recipe)
            }

            previousPageUrl = request.url.toString()
            nextPageUrl = rawResponse?._links?.next?.href

            return Result.Success(response!!)
        }
    }
}