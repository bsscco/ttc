package com.example.data.api

import com.example.data.subway.SubwayResponse
import retrofit2.http.GET
import retrofit2.http.Headers


internal interface RetrofitApiService {

    @GET("v1/filter/subway/version/1")
    @Headers("Content-Type: application/json")
    suspend fun getSubway(): SubwayResponse
}