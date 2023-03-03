package com.serkancay.knowcean.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.serkancay.knowcean.BuildConfig
import com.serkancay.knowcean.constant.Constants
import com.serkancay.knowcean.constant.PreferencesKeys
import com.serkancay.knowcean.network.api.WikiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(dataStore: DataStore<Preferences>): String {
        return runBlocking {
            dataStore.data.map {
                when(it[PreferencesKeys.LANGUAGE] ?: "en") {
                    "en" -> Constants.API_BASE_URL_EN
                    "tr" -> Constants.API_BASE_URL_TR
                    else -> Constants.API_BASE_URL_EN
                }
            }.first()
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideWikiService(retrofit: Retrofit) = retrofit.create(WikiService::class.java)

}