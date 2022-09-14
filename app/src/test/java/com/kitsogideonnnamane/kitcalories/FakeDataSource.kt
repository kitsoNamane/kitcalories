package com.kitsogideonnnamane.kitcalories

import com.kitsogideonnnamane.kitcalories.data.Recipe
import com.kitsogideonnnamane.kitcalories.data.Result
import com.kitsogideonnnamane.kitcalories.data.source.RecipeDataSource
import kotlinx.coroutines.flow.Flow

class FakeDataSource : RecipeDataSource {
    override suspend fun saveRecipes(recipes: List<Recipe>) {
        TODO("Not yet implemented")
    }

    override suspend fun favouriteRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun unFavouriteRecipes(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override fun getFavouriteRecipesStream(): Flow<Result<List<Recipe>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavouriteRecipes(): Result<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override fun getRecipeStream(recipeId: String): Flow<Result<List<Recipe>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipe(recipeId: String): Result<Recipe> {
        TODO("Not yet implemented")
    }
}