package com.kitsogideonnnamane.kitcalories.data.source

import com.kitsogideonnnamane.kitcalories.data.Recipe
import com.kitsogideonnnamane.kitcalories.data.Result
import kotlinx.coroutines.flow.Flow

class DefaultRecipeRepository(
    val localDataStore: RecipeDataSource,
    val remoteDataStore: RecipeDataSource,
) : RecipeRepository {
    override suspend fun saveRecipes(recipes: List<Recipe>) {
        TODO("Not yet implemented")
    }

    override suspend fun favouriteRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun unFavouriteRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override fun getFavouriteRecipesStream(): Flow<Result<List<Recipe>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavouriteRecipes(forceUpdate: Boolean): Result<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override fun getRecipeStream(recipeId: String): Flow<Result<List<Recipe>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipe(recipeId: String, forceUpdate: Boolean): Result<Recipe> {
        TODO("Not yet implemented")
    }

}