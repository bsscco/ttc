package com.example.domain.subway

import com.example.domain.CoroutineUseCase
import com.example.domain.subway.DeleteFavoriteStationUseCase.DeleteFavoriteStationParams
import com.example.util.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteFavoriteStationUseCase @Inject constructor(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
    private val subwayRepository: SubwayRepository,
) : CoroutineUseCase<DeleteFavoriteStationParams, Unit>(defaultDispatcher) {

    override suspend fun execute(parameters: DeleteFavoriteStationParams) = subwayRepository.deleteFavoriteStation(parameters.stationIdx)

    data class DeleteFavoriteStationParams(val stationIdx: Int)
}