package com.example.ryanrileymtgfinal2.utils

import com.bumptech.glide.load.model.GlideUrl

object GlideHelper {

    private const val placeholder = "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=386483&type=card"

    fun getUrl(url: String?): GlideUrl {
        return if (url == null){
            GlideUrl(placeholder)
        }else {
            GlideUrl(url)
        }
    }
}