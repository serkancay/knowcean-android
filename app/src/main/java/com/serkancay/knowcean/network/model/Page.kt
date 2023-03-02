package com.serkancay.knowcean.network.model

import com.google.gson.annotations.SerializedName

data class Page(
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @field:SerializedName("extract")
    val extract: String
)

data class Thumbnail(
    @field:SerializedName("source")
    val source: String
)