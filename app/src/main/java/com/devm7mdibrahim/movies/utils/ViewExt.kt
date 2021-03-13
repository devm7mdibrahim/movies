package com.devm7mdibrahim.movies.utils

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(url: String) = Picasso.get().load(url).into(this)