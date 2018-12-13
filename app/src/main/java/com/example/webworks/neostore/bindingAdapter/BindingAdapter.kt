package com.example.webworks.neostore.bindingAdapter

import android.databinding.BindingAdapter
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide


@BindingAdapter("srcUrl")
fun setImage(viewImage: ImageView, imgURL: String) {
    Glide.with(viewImage.context).load(imgURL).into(viewImage)
}


