package com.kitsogideonnnamane.kitcalories

import com.google.common.truth.Truth.assertThat
import com.kitsogideonnnamane.kitcalories.data.Result
import com.kitsogideonnnamane.kitcalories.data.source.RecipeSearchApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class RecipeSearchApiTest {
    private lateinit var recipeSearchApi: RecipeSearchApi
    private lateinit var mockWebServer: MockWebServer

    private fun readMockJsonResponse(fileName: String): String {
        val resource = javaClass.classLoader?.getResource(fileName)
        val file = File(resource?.path!!)
        return file.readText(Charsets.UTF_8)
    }

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        val dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                if (request.path?.contains("/search")!!) {
                    return MockResponse().setBody(readMockJsonResponse("success_search_response.json"))
                } else if (request.path?.contains("/recipe")!!) {
                    val response = readMockJsonResponse("success_recipe_response.json")
                    return MockResponse().setBody(readMockJsonResponse("success_recipe_response.json"))
                }
                return MockResponse().setResponseCode(404)
                    .setBody(readMockJsonResponse("fail_recipe_response.json"))
            }
        }
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start(9090)
        recipeSearchApi = FakeRecipeSearchApi(OkHttpClient())
    }

    @Test
    fun testTestResources() {
        val resource = javaClass.classLoader?.getResource("success_recipe_response.json")
        val file = File(resource?.path!!)
        assertThat(file.exists()).isTrue()
        assertThat(file.readLines().size).isGreaterThan(10)
    }

    @Test
    fun test_recipeSearchApi_serverOnline() {
        val client = OkHttpClient()
        val request = Request.Builder().url("http://localhost:9090/404").build()

        client.newCall(request).execute().use { res ->
            assertThat(res.isSuccessful).isFalse()
            assertThat(res.code).isEqualTo(404)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_a_recipe() = runTest {
        val response = recipeSearchApi.getRecipe("fakeRecipeId")
        response as Result.Success
        assertThat(response.data.title).isEqualTo("Chicken Vesuvio")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun search_recipes() = runTest {
        val searchString = "rice"
        val response = recipeSearchApi.searchRecipes(searchString)
        assertThat(response).isInstanceOf(Result.Success::class.java)
        response as Result.Success

        assertThat(response.data.size).isGreaterThan(0)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun nextPage_getSearchResultsNextPage() = runTest {
        recipeSearchApi.searchRecipes("rice")
        val response = recipeSearchApi.nextPage()
        assertThat(response).isInstanceOf(Result.Success::class.java)
        response as Result.Success
        assertThat(response.data.size).isGreaterThan(0)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun previousPage_getSearchResultsPreviousPage() = runTest {
        recipeSearchApi.searchRecipes("rice")
        val response = recipeSearchApi.nextPage()
        response as Result.Success
        assertThat(response.data.size).isGreaterThan(0)
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}