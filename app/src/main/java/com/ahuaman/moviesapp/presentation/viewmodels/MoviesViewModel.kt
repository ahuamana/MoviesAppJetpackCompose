package com.ahuaman.moviesapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuaman.moviesapp.domain.MovieDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(

):ViewModel(){

    private val _movies = MutableStateFlow<List<MovieDomain>>(emptyList())
    val movies = _movies.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000,1),
        initialValue = emptyList()
    )


}