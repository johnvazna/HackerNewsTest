package com.hackernews.app.di

import com.hackernews.app.di.feature.hit.hitModule
import org.koin.core.module.Module

/** */
fun getFeatureModules(): List<Module> = listOf(
    hitModule,
)