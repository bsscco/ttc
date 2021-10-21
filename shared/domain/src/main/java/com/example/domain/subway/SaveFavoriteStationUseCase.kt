package com.example.domain.subway

import com.example.domain.CoroutineUseCase
import com.example.domain.subway.SaveFavoriteStationUseCase.SaveFavoriteStationParams
import com.example.util.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveFavoriteStationUseCase @Inject constructor(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
    private val subwayRepository: SubwayRepository,
) : CoroutineUseCase<SaveFavoriteStationParams, Unit>(defaultDispatcher) {

    override suspend fun execute(parameters: SaveFavoriteStationParams) = subwayRepository.insertFavoriteStation(parameters.stationIdx)

    data class SaveFavoriteStationParams(val stationIdx: Int)
}