package com.kitsogideonnnamane.kitcalories

import com.google.common.truth.Truth.assertThat
import com.kitsogideonnnamane.kitcalories.data.Result
import com.kitsogideonnnamane.kitcalories.data.source.RecipeSearchApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RecipeSearchApiTest {
    private lateinit var recipeSearchApi: RecipeSearchApi

    @Before
    fun setup() {
        recipeSearchApi = FakeRecipeSearchApi()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun search_recipes() = runTest {
        val searchString = "rice"
        assertThat(recipeSearchApi.searchRecipes(searchString) is Result.Success).isTrue()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun nextPage_getSearchResultsNextPage() = runTest {
       assertThat(recipeSearchApi.nextPage() is Result.Success).isTrue()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun previousPage_getSearchResultsPreviousPage() = runTest {
        assertThat(recipeSearchApi.previousPage() is Result.Success).isTrue()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_a_recipe() = runTest {
        assertThat(recipeSearchApi.getRecipe("fakeRecipeId") is Result.Success).isTrue()
    }

}