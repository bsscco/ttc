package com.example.data.di

import android.content.Context
import com.example.data.BuildConfig
import com.example.data.api.ApiService
import com.example.data.api.RetrofitApiService
import com.example.data.api.RetrofitApiServiceWrapper
import com.example.data.db.AppDestructibleDatabase
import com.example.data.subway.DefaultSubwayRepository
import com.example.data.subway.RoomSubwayDao
import com.example.domain.subway.SubwayRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideSubwayRepository(repository: DefaultSubwayRepository): SubwayRepository

    companion object {
        @Singleton
        @Provides
        fun provideHttpClient() = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            )
            .build()

        @OptIn(ExperimentalSerializationApi::class)
        @Singleton
        @ProductionApiService
        @Provides
        fun provideApiService(httpClient: OkHttpClient): ApiService = RetrofitApiServiceWrapper(
            Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .baseUrl(BuildConfig.apiHost)
                .build()
                .create(RetrofitApiService::class.java)
        )

        @OptIn(ExperimentalSerializationApi::class)
        @Singleton
        @DevApiService
        @Provides
        fun provideDevApiService(httpClient: OkHttpClient): ApiService = RetrofitApiServiceWrapper(
            Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .baseUrl(BuildConfig.devApiHost)
                .build()
                .create(RetrofitApiService::class.java)
        )

        @Singleton
        @Provides
        fun provideAppDestructibleDatabase(@ApplicationContext context: Context): AppDestructibleDatabase = AppDestructibleDatabase.buildDatabase(context)

        @Singleton
        @Provides
        fun provideSubwayDao(db: AppDestructibleDatabase): RoomSubwayDao = db.getSubwayDao()
    }
}