package com.greedygamepractical.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.greedygamepractical.data.repository.GenreDetailsRepository
import com.greedygamepractical.data.repository.GenreRepository
import com.greedygamepractical.di.ActivityContext
import com.greedygamepractical.ui.base.ViewModelProviderFactory
import com.greedygamepractical.ui.genre.GenreViewModel
import com.greedygamepractical.ui.genreDetails.GenreDetailsViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideGenreViewModel(genreRepository: GenreRepository): GenreViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(GenreViewModel::class) {
                GenreViewModel(genreRepository)
            })[GenreViewModel::class.java]
    }


    @Provides
    fun provideGenreDetailsViewModel(genreDetailsRepository: GenreDetailsRepository): GenreDetailsViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(GenreDetailsViewModel::class) {
                GenreDetailsViewModel(genreDetailsRepository)
            })[GenreDetailsViewModel::class.java]
    }

}