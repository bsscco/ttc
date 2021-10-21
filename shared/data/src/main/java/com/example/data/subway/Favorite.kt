package com.example.data.subway

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "favorites")
internal data class Favorite(
    @PrimaryKey val stationIdx: Int,
)