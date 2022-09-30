package com.kitsogideonnnamane.kitcalories.data.source

import com.kitsogideonnnamane.kitcalories.data.Result
import kotlinx.coroutines.flow.Flow

interface RecipeDataSource {
    suspend fun saveRecipes(recipes: List<Recipe>)

    suspend fun deleteRecipes(recipe: Recipe)

    suspend fun favouriteRecipe(recipe: Recipe)

    suspend fun unFavouriteRecipes(recipe: Recipe)

    fun getFavouriteRecipesStream(): Flow<Result<List<Recipe>>>

    suspend fun getFavouriteRecipes(): Result<List<Recipe>>

    fun getRecipeStream(recipeId: String): Flow<Result<List<Recipe>>>

    suspend fun getRecipe(recipeId: String): Result<Recipe>
}