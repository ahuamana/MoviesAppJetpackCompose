package com.ahuaman.moviesapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class DetailsSharedViewModel @Inject constructor() : ViewModel(){

    private val _movieId = mutableStateOf(0)


    fun setMovieId(movieId: Int){
        _movieId.value = movieId
    }

    fun getMovieId(): Int{
        return _movieId.value
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("DetailsSharedViewModel onCleared")
    }
}