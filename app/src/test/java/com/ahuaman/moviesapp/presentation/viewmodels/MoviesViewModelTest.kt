package com.ahuaman.moviesapp.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahuaman.moviesapp.domain.toDomainModel
import com.ahuaman.moviesapp.usecases.GetPopularMoviesUseCase
import com.ahuaman.moviesapp.usecases.PopularMoviesResult
import com.ahuaman.moviesapp.usecases.SearchMovieUseCase
import com.ahuaman.moviesapp.usecases.data.FakeRepositorySuccessApi
import com.ahuaman.moviesapp.usecases.fakes.FakeValueApi
import com.ahuaman.moviesapp.usecases.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.Duration.Companion.seconds

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    lateinit var fakeRepositorySuccess: FakeRepositorySuccessApi

    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    lateinit var searchMoviesUseCase: SearchMovieUseCase

    // SUT -> System Under Test
    private lateinit var sut: MoviesViewModel

    private val listResult = mutableListOf<PopularMoviesResult>()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        fakeRepositorySuccess = FakeRepositorySuccessApi()
        getPopularMoviesUseCase = GetPopularMoviesUseCase(fakeRepositorySuccess)
        searchMoviesUseCase = SearchMovieUseCase(fakeRepositorySuccess)

        sut = MoviesViewModel(getPopularMoviesUseCase, searchMoviesUseCase)


    }

    @Test
    fun `get popular movies should return different results emitted`() = mainCoroutineScopeRule.runBlockingTest {
        // Arrange
        val scope = launch {
            sut.movies.collect(){
                listResult.add(it)
            }
        }

        // Act
        sut.getPopularMovies()

        // Assert -> expected = actual
        val expected = listOf(
            PopularMoviesResult.Loading(true),
            PopularMoviesResult.Loading(false),
        )

        scope.cancel()
        assertNotEquals(expected, listResult)
    }

    @Test
    fun `get popular test first emit should be loading`() = runTest {
        // Arrange
        sut.movies.first(){
            listResult.add(it)
        }

        // Act
        sut.getPopularMovies()

        // Assert
        val expected = PopularMoviesResult.Loading(true)
        assertEquals(expected, listResult.first())
    }

    @Test
    fun `get popular normal test should return success`() = runTest {
        // Arrange
        val list = sut.movies.take(2).toList()

        // Act
        sut.getPopularMovies()

        // Assert
        val expected = listOf(
            PopularMoviesResult.Loading(true),
            PopularMoviesResult.Success(FakeValueApi.listMovieEntityFake().toDomainModel()),
        )
        assertEquals(expected, list)
    }


    @After
    fun tearDown() {
        listResult.clear()
        //kill the coroutine
        mainCoroutineScopeRule.coroutineContext.cancelChildren()
    }
}