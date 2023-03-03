package com.serkancay.knowcean.network

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.serkancay.knowcean.constant.Constants
import com.serkancay.knowcean.constant.PreferencesKeys
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class LanguageSelectionInterceptor(private val dataStore: DataStore<Preferences>) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // TODO Refactor retrieving host mechanism
        val host = runBlocking {
            dataStore.data.map {
                when (it[PreferencesKeys.LANGUAGE] ?: "en") {
                    "en" -> Constants.HOST_EN
                    "tr" -> Constants.HOST_TR
                    else -> Constants.HOST_EN
                }
            }.first()
        }

        val newUrl = chain.request().url.newBuilder()
            .host(host)
            .build()

        val request = chain.request().newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(request)
    }

}