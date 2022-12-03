package com.example.moviles_vinils_app_grupo_32.ui.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, cover: String?) {

    if (cover != null) {
        Glide.with(imgView.context)
            .load(cover.toUri().buildUpon().scheme("https").build())
            .apply(
                RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL))
            .into(imgView)
    }

}

