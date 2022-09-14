package com.kitsogideonnnamane.kitcalories

import com.kitsogideonnnamane.kitcalories.data.Recipe
import com.kitsogideonnnamane.kitcalories.data.Result
import com.kitsogideonnnamane.kitcalories.data.source.RecipeSearchApi

class FakeRecipeSearchApi : RecipeSearchApi {
    override suspend fun searchRecipes(searchQuery: String): Result<List<Recipe>> {
        return Result.Success(listOf<Recipe>())
    }

    override suspend fun getRecipe(recipeId: String): Result<Recipe> {
        return Result.Success(
            Recipe(
                title = "fake title",
                description = "fake description",
                isFavorite = false
            )
        )
    }

    override suspend fun nextPage(): Result<List<Recipe>> {
        return Result.Success(listOf<Recipe>())
    }

    override suspend fun previousPage(): Result<List<Recipe>> {
        return Result.Success(listOf<Recipe>())
    }
}