package com.devm7mdibrahim.movies.ui.details

import androidx.lifecycle.*
import com.devm7mdibrahim.movies.data.model.details.MovieDetailsResponse
import com.devm7mdibrahim.movies.data.repo.details.DetailsRepository
import com.devm7mdibrahim.movies.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movie = MutableStateFlow<DataState<MovieDetailsResponse>>(DataState.Idle)

    val movie: MutableStateFlow<DataState<MovieDetailsResponse>>
        get() = _movie

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            detailsRepository
                .getMovieDetails(movieId)
                .onEach {
                    _movie.value = it
                }.launchIn(viewModelScope)
        }
    }
}