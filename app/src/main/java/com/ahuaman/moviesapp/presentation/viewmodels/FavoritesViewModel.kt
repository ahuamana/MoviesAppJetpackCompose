package com.ahuaman.moviesapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuaman.moviesapp.domain.local.FavoriteMoviesEntity
import com.ahuaman.moviesapp.usecases.GetFavoriteMovieByIdUseCase
import com.ahuaman.moviesapp.usecases.GetFavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
):ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<FavoriteMoviesEntity>>(emptyList())
    val favoriteMovies = _favoriteMovies

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() = viewModelScope.launch(Dispatchers.IO) {
        getFavoriteMoviesUseCase
            .invoke()
            .onStart {
                //Loading
            }.onEach {
                _favoriteMovies.value = it
            }.catch {
                _favoriteMovies.value = emptyList()
            }.launchIn(viewModelScope)
    }
}