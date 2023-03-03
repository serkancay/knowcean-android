package com.serkancay.knowcean.network.model

sealed class Response<out T> {
    object Loading : Response<Nothing>()

    data class Success<out T>(
        val data: T
    ) : Response<T>()

    data class Failure(
        val exception: Exception?
    ) : Response<Nothing>()
}