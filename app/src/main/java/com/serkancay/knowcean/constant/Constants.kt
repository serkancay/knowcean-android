package com.serkancay.knowcean.constant

import androidx.annotation.DrawableRes
import com.serkancay.knowcean.R

object Constants {
    const val API_BASE_URL = "https://en.wikipedia.org/api/rest_v1/"
    val languages = listOf(
        Language("en", "English", R.drawable.flag_us),
        Language("tr", "Türkçe", R.drawable.flag_tr)
    )
}

data class Language(
    val code: String,
    val title: String,
    @DrawableRes val iconRes: Int
)