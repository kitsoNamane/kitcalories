package com.kitsogideonnnamane.kitcalories

import com.google.common.truth.Truth.assertThat
import com.kitsogideonnnamane.kitcalories.data.Result.Success
import com.kitsogideonnnamane.kitcalories.data.source.DefaultRecipeRepository
import com.kitsogideonnnamane.kitcalories.data.source.RecipeDataSource
import com.kitsogideonnnamane.kitcalories.data.source.RecipeRepository
import com.kitsogideonnnamane.kitcalories.data.source.RecipeSearchApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RecipeRepositoryTest {
    private lateinit var recipeRepository: RecipeRepository
    private lateinit var localDataStore: RecipeDataSource
    private lateinit var remoteDataStore: RecipeDataSource


    @Before
    fun setup() {
        localDataStore = FakeDataSource()
        remoteDataStore = FakeDataSource()
        recipeRepository = DefaultRecipeRepository(localDataStore, remoteDataStore)
    }

    @Test
    fun initialize_RecipeSearchRepository() {
        localDataStore = FakeDataSource()
        remoteDataStore = FakeDataSource()
        val repository = DefaultRecipeRepository(localDataStore, remoteDataStore)
        assertThat(repository).isNotNull()
    }


}