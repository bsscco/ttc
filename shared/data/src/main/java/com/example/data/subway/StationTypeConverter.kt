package com.example.data.subway

import androidx.room.TypeConverter
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
internal class StationTypeConverter {
    @TypeConverter
    fun subwayLinesToString(subwayLines: List<Int>) = Json.encodeToString(subwayLines)

    @TypeConverter
    fun stringToSubwayLines(value: String) = Json.decodeFromString<List<Int>>(value)
}