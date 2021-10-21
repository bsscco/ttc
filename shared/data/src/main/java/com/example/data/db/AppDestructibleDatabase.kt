package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.subway.Favorite
import com.example.data.subway.Line
import com.example.data.subway.RoomSubwayDao
import com.example.data.subway.Station
import com.example.data.subway.StationLineCrossRef
import com.example.data.subway.StationWithLines

@Database(
    entities = [
        Station::class,
        Line::class,
        StationLineCrossRef::class,
        Favorite::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDestructibleDatabase : RoomDatabase() {

    internal abstract fun getSubwayDao(): RoomSubwayDao

    companion object {
        private const val DATABASE_NAME = "ttc-destructible-db"

        fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDestructibleDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}