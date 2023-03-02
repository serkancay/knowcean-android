package com.serkancay.knowcean.network.api

import com.serkancay.knowcean.network.model.Page
import retrofit2.http.GET

interface WikiService {

    @GET("page/random/summary")
    suspend fun getRandomPage(): Page

}