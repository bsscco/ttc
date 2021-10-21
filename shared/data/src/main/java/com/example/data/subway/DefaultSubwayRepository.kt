package com.example.data.subway

import androidx.room.withTransaction
import com.example.data.api.ApiService
import com.example.data.db.AppDestructibleDatabase
import com.example.data.di.ProductionApiService
import com.example.domain.subway.SubwayRepository
import com.example.util.withResult
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(FlowPreview::class)
internal class DefaultSubwayRepository @Inject constructor(
    @ProductionApiService private val apiService: ApiService,
    private val localDb: AppDestructibleDatabase,
    private val subwayDao: RoomSubwayDao,
) : SubwayRepository {

    override fun getStationsFlow() = flow { emit(fillRemoteStations()) }
        .flatMapConcat { subwayDao.getStationsFlow() }
        .map { stations ->
            withResult {
                StationDtoCreator.createList(stations)
            }
        }

    private suspend fun fillRemoteStations() {
        if (subwayDao.getStationsSize() == 0) {
            val subway = apiService.getSubway()
            localDb.withTransaction {
                subwayDao.insertStations(subway.subwayStations)
                subwayDao.insertLines(subway.subwayLines)
                val crossRefs = subway.subwayStations.flatMap { station ->
                    station.subwayLines.map { lineIdx ->
                        StationLineCrossRef(
                            stationIdx = station.stationIdx,
                            lineIdx = lineIdx,
                        )
                    }
                }
                subwayDao.insertStationLineCrossRefs(crossRefs)
            }
        }
    }

    override fun getStationsFlow(searchText: String) = flow { emit(fillRemoteStations()) }
        .flatMapConcat { subwayDao.getStationsFlow(searchText) }
        .map { stations ->
            withResult {
                StationDtoCreator.createList(stations)
            }
        }

    override suspend fun insertFavoriteStation(stationIdx: Int) = subwayDao.insertFavorite(Favorite(stationIdx))

    override suspend fun deleteFavoriteStation(stationIdx: Int) = subwayDao.deleteFavorite(stationIdx)

    override suspend fun clearFavoriteStations() = subwayDao.clearFavorites()

    override fun getFavoriteStationsFlow() = flow { emit(fillRemoteStations()) }
        .flatMapConcat { subwayDao.getFavoriteStationsFlow() }
        .map { stations ->
            withResult {
                StationDtoCreator.createList(stations)
            }
        }
}