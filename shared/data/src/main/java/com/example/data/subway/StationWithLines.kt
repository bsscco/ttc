package com.example.data.subway

import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

@Keep
internal data class StationWithLines(
    @Embedded val station: Station,
    @Relation(
        parentColumn = "stationIdx",
        entityColumn = "lineIdx",
        associateBy = Junction(StationLineCrossRef::class),
    )
    val lines: List<Line>
)
