package com.ahuaman.moviesapp.usecases

import com.ahuaman.moviesapp.domain.MovieDomain
import com.ahuaman.moviesapp.usecases.data.FakeRepositoryErrorApi
import com.ahuaman.moviesapp.usecases.data.FakeRepositorySuccessApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class GetPopularMoviesUseCaseTest {

    // SUT -> System Under Test
    private lateinit var sut: GetPopularMoviesUseCase
    private lateinit var sutSuccess: GetPopularMoviesUseCase

    // DOC -> Depedency of Component
    private lateinit var fakeRepositoryFailureApi: FakeRepositoryErrorApi
    private lateinit var fakeRepositorySuccessApi: FakeRepositorySuccessApi

    //Save collect result
    private val listMovies = mutableListOf<MovieDomain>()

    @Before
    fun setUp() {
        fakeRepositoryFailureApi = FakeRepositoryErrorApi()
        sut = GetPopularMoviesUseCase(fakeRepositoryFailureApi)

        fakeRepositorySuccessApi = FakeRepositorySuccessApi()
        sutSuccess = GetPopularMoviesUseCase(fakeRepositorySuccessApi)
    }

    @Test(expected = HttpException::class)
    fun `should return exception when network request is failed`() = runBlocking{
        //Arrange

        //Act
        val result = sut.invoke(
            api_key = "1234567890",
            language = "en-US",
            page = 1
        )

        result.collect {
            //Assert
            listMovies += it
        }

        //Assert
        assert(listMovies.isEmpty())
    }

    @Test
    fun `should return success with list converted to domain when network request is success`() = runBlocking{
        //Arrange

        //Act
        val result = sutSuccess.invoke(
            api_key = "1234567890",
            language = "en-US",
            page = 1
        )

        result.collect {
            //Assert
            listMovies += it
        }

        //Assert - Verify
        assert(listMovies.isNotEmpty())
    }




    @After
    fun tearDown() {
    }
}