package com.example.domain.subway

import com.example.util.Result
import kotlinx.coroutines.flow.Flow

interface SubwayRepository {

    fun getStationsFlow(): Flow<Result<List<StationDto>>>

    fun getStationsFlow(searchText: String): Flow<Result<List<StationDto>>>

    suspend fun insertFavoriteStation(stationIdx: Int)

    suspend fun deleteFavoriteStation(stationIdx: Int)

    suspend fun clearFavoriteStations()

    fun getFavoriteStationsFlow(): Flow<Result<List<StationDto>>>
}