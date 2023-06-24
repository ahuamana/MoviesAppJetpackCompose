package com.ahuaman.moviesapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuaman.moviesapp.BuildConfig
import com.ahuaman.moviesapp.domain.MovieDomain
import com.ahuaman.moviesapp.usecases.GetPopularMoviesUseCase
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
    val getPopularMoviesUseCase: GetPopularMoviesUseCase
):ViewModel(){

    private val _movies = MutableStateFlow<List<MovieDomain>>(emptyList())
    val movies = _movies.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000,1),
        initialValue = emptyList()
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
            //emptyList()
        }.onEach {
            _movies.value = it
        }.catch {

        }.launchIn(viewModelScope)
    }

}