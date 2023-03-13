package com.greedygamepractical

import android.app.Application
import com.greedygamepractical.di.component.ApplicationComponent
import com.greedygamepractical.di.component.DaggerApplicationComponent
import com.greedygamepractical.di.module.ApplicationModule


class GreedyGamePractical : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}