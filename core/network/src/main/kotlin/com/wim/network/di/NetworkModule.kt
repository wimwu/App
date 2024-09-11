package com.wim.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.wim.network.BuildConfig
import com.wim.network.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun okHttpCallFactory(
        headerInterceptor: HeaderInterceptor
    ): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }
        )
        .addInterceptor(headerInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        json: Json,
        okHttpCallFactory: Call.Factory
    ): Lazy<Retrofit> = lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callFactory(okHttpCallFactory)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}