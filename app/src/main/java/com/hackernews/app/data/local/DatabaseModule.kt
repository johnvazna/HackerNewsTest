package com.hackernews.app.data.local

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: Module = module {

    /* */
    single { ApplicationDatabase(androidContext()) }

    /* */
    single { get<ApplicationDatabase>().hintDao() }

}