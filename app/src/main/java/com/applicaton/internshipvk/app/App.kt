package com.applicaton.internshipvk.app

import android.app.Application
import com.applicaton.internshipvk.di.AppComponent
import com.applicaton.internshipvk.di.DaggerAppComponent

class App : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}