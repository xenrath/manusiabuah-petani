package com.xenrath.manusiabuahpetani.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.xenrath.manusiabuahpetani.R

class GlideHelper {

    companion object {

        fun setImage(context: Context, urlImage: String, imageView: ImageView) {
            Glide.with(context)
                .load(urlImage)
                .centerCrop()
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .into(imageView)
        }

    }

}