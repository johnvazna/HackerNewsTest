package com.hackernews.app.di

import com.hackernews.app.data.local.databaseModule
import org.koin.core.module.Module

/** */
fun getDataModules(): List<Module> = listOf(
    databaseModule,
)