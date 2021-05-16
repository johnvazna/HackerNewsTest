package com.hackernews.app.di

import com.hackernews.app.di.shared.httpClient.httpClientModule
import org.koin.core.module.Module

/** */
fun getSharedModules(): List<Module> = listOf(
    httpClientModule,
)