package com.example.data.subway

import com.example.domain.subway.StationDto

internal object StationDtoCreator {

    fun createList(stationWithLines: List<StationWithLines>) = stationWithLines.map { stationWithLine ->
        StationDto(
            idx = stationWithLine.station.stationIdx,
            name = stationWithLine.station.name,
            lines = stationWithLine.lines.map { line ->
                StationDto.Line(
                    idx = line.lineIdx,
                    shortName = line.subName,
                    colorCode = line.colorCode,
                )
            },
        )
    }
}