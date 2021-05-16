package com.hackernews.app

import android.app.Application
import android.content.Context
import com.hackernews.app.di.initKoin

class HackerApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: HackerApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        applicationContext()
        initKoin()
    }

}