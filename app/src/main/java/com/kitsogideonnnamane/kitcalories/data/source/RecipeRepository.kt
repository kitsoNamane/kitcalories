package com.kitsogideonnnamane.kitcalories.data.source

import com.kitsogideonnnamane.kitcalories.data.Recipe
import com.kitsogideonnnamane.kitcalories.data.Result
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun saveRecipes(recipes: List<Recipe>)

    suspend fun favouriteRecipe(recipe: Recipe)

    suspend fun unFavouriteRecipe(recipe: Recipe)

    fun getFavouriteRecipesStream(): Flow<Result<List<Recipe>>>

    suspend fun getFavouriteRecipes(forceUpdate: Boolean = false): Result<List<Recipe>>

    fun getRecipeStream(recipeId: String): Flow<Result<List<Recipe>>>

    suspend fun getRecipe(recipeId: String, forceUpdate: Boolean = false): Result<Recipe>

}