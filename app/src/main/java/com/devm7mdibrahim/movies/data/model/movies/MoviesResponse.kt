package com.devm7mdibrahim.movies.data.model.movies

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val movies: List<Movie>? = emptyList()
)