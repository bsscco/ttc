package com.example.domain.subway

internal object StationCreator {

    fun createList(stations: List<StationDto>) = stations.map { station ->
        Station(
            idx = station.idx,
            name = station.name,
            lines = station.lines.map { line ->
                Station.Line(
                    idx = line.idx,
                    name = line.shortName,
                    colorCode = line.colorCode,
                )
            },
        )
    }
}