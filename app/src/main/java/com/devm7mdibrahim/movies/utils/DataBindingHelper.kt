package com.devm7mdibrahim.movies.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("loadImage")
fun loadMovieImage(imageView: ImageView, url: String?) {
    url?.let {
        Picasso.get().load(IMAGE_BASE_URL + it).fit().into(imageView)
    }
}