package com.ahuaman.moviesapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuaman.moviesapp.BuildConfig
import com.ahuaman.moviesapp.usecases.GetPopularMoviesUseCase
import com.ahuaman.moviesapp.usecases.PopularMoviesResult
import com.ahuaman.moviesapp.usecases.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
):ViewModel(){

    private val _moviesStateResult = MutableStateFlow<PopularMoviesResult>(PopularMoviesResult.Loading(false))
    val movies = _moviesStateResult.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000,1),
        initialValue = PopularMoviesResult.Loading(false)
    )

    init {
        getPopularMovies()
    }

    fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO){
        getPopularMoviesUseCase.invoke(
            api_key = BuildConfig.API_KEY,
            language = "es-ES",
            page = 1
        ).onStart {
            _moviesStateResult.value = PopularMoviesResult.Loading(true)
        }.onEach {
            _moviesStateResult.value = PopularMoviesResult.Success(it)
        }.catch {
            when(it){
                //TODO: Add more cases Internet connection, etc
                else -> {
                    _moviesStateResult.value = PopularMoviesResult.ErrorGeneral(it.message?: "Error general")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchMovieOrEmpty(query:String){
        if(query.isEmpty()){
            getPopularMovies()
            return
        }
        // Search movie only if query is not empty
        searchMovie(query)
    }

    private fun searchMovie(query:String) = viewModelScope.launch(Dispatchers.IO){
        searchMovieUseCase.invoke(
            api_key = BuildConfig.API_KEY,
            language = "es-ES",
            query = query,
            //page = 1
        ).onStart {
            _moviesStateResult.value = PopularMoviesResult.Loading(true)
        }.onEach {
            if(it.isEmpty()){
                _moviesStateResult.value = PopularMoviesResult.Empty
                return@onEach
            }
            _moviesStateResult.value = PopularMoviesResult.Success(it)
        }.catch {
            when(it){
                else -> {
                    _moviesStateResult.value = PopularMoviesResult.ErrorGeneral(it.message?: "Error general")
                }
            }
        }.launchIn(viewModelScope)
    }

}