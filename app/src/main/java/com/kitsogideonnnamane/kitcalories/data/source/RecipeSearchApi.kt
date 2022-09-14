package com.kitsogideonnnamane.kitcalories.data.source

import com.kitsogideonnnamane.kitcalories.data.Recipe
import com.kitsogideonnnamane.kitcalories.data.Result

interface RecipeSearchApi {
    suspend fun searchRecipes(searchQuery: String) : Result<List<Recipe>>

    suspend fun getRecipe(recipeId: String) : Result<Recipe>

    suspend fun nextPage() : Result<List<Recipe>>

    suspend fun previousPage() : Result<List<Recipe>>
}