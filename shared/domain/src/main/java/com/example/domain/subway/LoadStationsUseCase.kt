package com.example.domain.subway

import com.example.domain.FlowUseCase
import com.example.domain.subway.LoadStationsUseCase.LoadStationsParams
import com.example.domain.subway.LoadStationsUseCase.LoadStationsParams.*
import com.example.util.di.DefaultDispatcher
import com.example.util.mapSuccessData
import com.example.util.withResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class LoadStationsUseCase @Inject constructor(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
    private val subwayRepository: SubwayRepository,
) : FlowUseCase<LoadStationsParams, List<Station>>(defaultDispatcher) {

    override fun execute(parameters: LoadStationsParams) = when (parameters) {
        is LoadAllStationsParams -> subwayRepository.getStationsFlow()
        is LoadStationsNameMatchedParams -> subwayRepository.getStationsFlow(parameters.searchText)
        is LoadFavoriteStationsParams -> subwayRepository.getFavoriteStationsFlow()
    }
        .distinctUntilChanged()
        .mapSuccessData { stations ->
            withResult { StationCreator.createList(stations) }
        }

    sealed interface LoadStationsParams {
        object LoadAllStationsParams : LoadStationsParams

        data class LoadStationsNameMatchedParams(val searchText: String) : LoadStationsParams

        object LoadFavoriteStationsParams : LoadStationsParams
    }
}