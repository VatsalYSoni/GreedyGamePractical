package com.greedygamepractical.di.component

import android.content.Context
import com.greedygamepractical.GreedyGamePractical
import com.greedygamepractical.data.api.NetworkService
import com.greedygamepractical.data.repository.GenreDetailsRepository
import com.greedygamepractical.data.repository.GenreRepository
import com.greedygamepractical.di.ApplicationContext
import com.greedygamepractical.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: GreedyGamePractical)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getGenreRepository(): GenreRepository

    fun getGenreDetailsRepository(): GenreDetailsRepository

}