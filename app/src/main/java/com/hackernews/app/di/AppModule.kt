package com.hackernews.app.di

import com.hackernews.app.HackerApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun HackerApplication.initKoin() {

    val dataModule = getDataModules()
    val modules = dataModule

    startKoin {
        androidLogger()
        androidContext(this@initKoin)
        modules(modules)
    }
}