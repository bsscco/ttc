package com.example.data.api

import com.example.data.subway.SubwayResponse

internal interface ApiService {

    suspend fun getSubway(): SubwayResponse
}