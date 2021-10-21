package com.example.data.subway

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Entity(tableName = "lines")
@Serializable
internal data class Line(
    @SerialName("idx") @PrimaryKey val lineIdx: Int,
    @SerialName("name") val name: String,
    @SerialName("sub_name") val subName: String,
    @SerialName("color_code") val colorCode: String,
)