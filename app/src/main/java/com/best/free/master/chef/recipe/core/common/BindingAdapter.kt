package com.best.free.master.chef.recipe.core.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.best.free.master.chef.recipe.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, url: String?) {
    val options = RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.placeholder)
    Glide.with(view)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(view)
}