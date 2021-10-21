package com.example.feed

import com.example.domain.subway.Station

internal object FavoriteListUiStateCreator {

    fun create(stations: List<Station>) = FavoriteListUiState(
        stations = stations.map { station ->
            FavoriteListUiState.Station(
                stationIdx = station.idx,
                name = station.name,
                lines = station.lines.map { line ->
                    FavoriteListUiState.Station.Line(
                        name = line.name,
                        colorCode = line.colorCode,
                    )
                }
            )
        }
    )
}