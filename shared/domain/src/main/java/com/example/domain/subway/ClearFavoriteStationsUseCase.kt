package com.example.domain.subway

import com.example.domain.CoroutineUseCase
import com.example.util.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ClearFavoriteStationsUseCase @Inject constructor(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
    private val subwayRepository: SubwayRepository,
) : CoroutineUseCase<Unit, Unit>(defaultDispatcher) {

    override suspend fun execute(parameters: Unit) = subwayRepository.clearFavoriteStations()
}