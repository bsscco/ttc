package com.example.data.subway

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Entity(tableName = "stations")
@TypeConverters(StationTypeConverter::class)
@Serializable
internal data class Station(
    @SerialName("idx") @PrimaryKey val stationIdx: Int,
    @SerialName("name") val name: String,
    @SerialName("subway_lines") val subwayLines: List<Int>,
)