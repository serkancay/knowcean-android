package com.serkancay.knowcean.repository

import com.serkancay.knowcean.network.api.WikiService
import com.serkancay.knowcean.network.model.Page
import com.serkancay.knowcean.network.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WikiRepository @Inject constructor(private val wikiService: WikiService) {
    fun getRandomPage(): Flow<Response<Page>> = flow {
        try {
            emit(Response.Loading)
            val response = wikiService.getRandomPage()
            emit(Response.Success(response))
        } catch (exception: Exception) {
            emit(Response.Failure(exception))
        }
    }.flowOn(Dispatchers.IO)
}