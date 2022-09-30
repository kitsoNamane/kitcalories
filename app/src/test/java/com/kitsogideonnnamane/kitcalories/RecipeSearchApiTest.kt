package com.kitsogideonnnamane.kitcalories

import com.google.common.truth.Truth.assertThat
import com.kitsogideonnnamane.kitcalories.data.Result
import com.kitsogideonnnamane.kitcalories.data.source.RecipeSearchApi
import com.kitsogideonnnamane.kitcalories.data.succeeded
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RecipeSearchApiTest {
    private lateinit var recipeSearchApi: RecipeSearchApi
    private lateinit var mockWebServer: MockWebServer

    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            mockWebServer.enqueue(mockResponse)
        }
    }

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        val dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                if (request.path?.contains("/search")!!) {
                    return MockResponse().setBody("search")
                } else if (request.path?.contains("/recipe")!!) {
                    return MockResponse().setBody("recipe")
                }
                return MockResponse().setResponseCode(404).setBody("404")
            }
        }
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start(9090)
        recipeSearchApi = FakeRecipeSearchApi(OkHttpClient())
    }

    @Test
    fun test_recipeSearchApi_serverOnline() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://localhost:9090/search")
            .build()

        client.newCall(request).execute().use { res ->
            if (!res.isSuccessful) throw IOException("Unexpected code $res")

            assertThat(res.code).isEqualTo(200)
            assertThat(res.body!!.string()).isEqualTo("search")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun search_recipes() = runTest {
        enqueueMockResponse("success_search_response.json")

        val searchString = "rice"
        val response = recipeSearchApi.searchRecipes(searchString)
        response as Result.Success

        assertThat(response.data.size).isGreaterThan(0)
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

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}