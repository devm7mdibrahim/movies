package com.devm7mdibrahim.movies.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("loadImage")
fun loadMovieImage(imageView: ImageView, url: String) {
    Picasso.get().load(IMAGE_BASE_URL+url).fit().into(imageView)
}