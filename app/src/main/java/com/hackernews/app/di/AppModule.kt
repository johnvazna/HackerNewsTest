package com.hackernews.app.di

import com.hackernews.app.HackerApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Initialize the Koin instance with the modules associated to the project.
 */
fun HackerApplication.initKoin() {

    val sharedModules = getSharedModules()
    val dataModules = getDataModules()
    val modules = sharedModules + dataModules

    startKoin {
        androidLogger()
        androidContext(this@initKoin)
        modules(modules)
    }
}