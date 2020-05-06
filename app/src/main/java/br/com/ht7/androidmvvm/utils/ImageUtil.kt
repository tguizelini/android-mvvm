package br.com.ht7.androidmvvm.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.ht7.androidmvvm.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(imageUrl: String?, progressDrawable: CircularProgressDrawable) {
    val requestOptions = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_photo)

    Glide.with(this.context)
        .setDefaultRequestOptions(requestOptions)
        .load(imageUrl)
        .into(this)
}