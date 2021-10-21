package com.example.data.subway

import androidx.annotation.Keep
import androidx.room.Entity

@Keep
@Entity(tableName = "stationLineCrossRefs", primaryKeys = ["stationIdx", "lineIdx"])
internal data class StationLineCrossRef(
    val stationIdx: Int,
    val lineIdx: Int
)