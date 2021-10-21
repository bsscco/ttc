package com.example.data.api

internal class RetrofitApiServiceWrapper(private val retrofitApiService: RetrofitApiService) : ApiService {

    override suspend fun getSubway() = retrofitApiService.getSubway()
}