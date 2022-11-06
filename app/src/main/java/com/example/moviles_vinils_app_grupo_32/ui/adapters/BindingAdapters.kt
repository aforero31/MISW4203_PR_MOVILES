package com.example.moviles_vinils_app_grupo_32.ui.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, cover: String?) {
    cover?.let {
        val imgUri = cover.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

