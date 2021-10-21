package com.example.data.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ProductionApiService

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DevApiService