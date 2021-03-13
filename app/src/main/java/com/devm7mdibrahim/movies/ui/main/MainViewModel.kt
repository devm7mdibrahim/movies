package com.devm7mdibrahim.movies.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devm7mdibrahim.movies.data.model.movies.Movie
import com.devm7mdibrahim.movies.data.repo.main.MainRepository
import com.devm7mdibrahim.movies.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movies = MutableStateFlow<DataState<List<Movie>>>(DataState.Idle)

    val movies: MutableStateFlow<DataState<List<Movie>>>
        get() = _movies

    fun getMovies() {
        viewModelScope.launch {
            mainRepository
                .getMovies()
                .onEach {
                    _movies.value = it
                }.launchIn(viewModelScope)
        }
    }
}