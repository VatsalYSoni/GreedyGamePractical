package com.greedygamepractical.di.component
import com.greedygamepractical.di.ActivityScope
import com.greedygamepractical.di.module.ActivityModule
import com.greedygamepractical.ui.genre.GenreActivity
import com.greedygamepractical.ui.genreDetails.GenreDetailsActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: GenreActivity)
    fun inject(activity: GenreDetailsActivity)
}