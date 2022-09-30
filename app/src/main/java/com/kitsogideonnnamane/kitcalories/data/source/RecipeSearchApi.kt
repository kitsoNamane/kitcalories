package com.kitsogideonnnamane.kitcalories.data.source

import com.kitsogideonnnamane.kitcalories.data.RecipeModel
import com.kitsogideonnnamane.kitcalories.data.Result

interface RecipeSearchApi {
    suspend fun searchRecipes(searchQuery: String) : Result<List<RecipeModel>>

    suspend fun getRecipe(recipeId: String) : Result<RecipeModel>

    suspend fun nextPage() : Result<List<RecipeModel>>

    suspend fun previousPage() : Result<List<RecipeModel>>
}