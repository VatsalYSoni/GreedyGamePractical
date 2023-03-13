package com.greedygamepractical.di.module

import android.content.Context
import com.greedygamepractical.GreedyGamePractical
import com.greedygamepractical.data.api.NetworkService
import com.greedygamepractical.di.ApplicationContext
import com.greedygamepractical.di.BaseUrl
import com.greedygamepractical.utils.AppConstant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: GreedyGamePractical) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = AppConstant.BASE_URL
    //fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    private var logging: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logging)

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(httpClient.build())
            .build()
            .create(NetworkService::class.java)
    }

}