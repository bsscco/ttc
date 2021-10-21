package com.example.data.subway

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RoomSubwayDao {

    @Query("SELECT * FROM stations")
    fun getStationsFlow(): Flow<List<StationWithLines>>

    @Query("SELECT * FROM stations WHERE name LIKE '%' || :searchText || '%'")
    fun getStationsFlow(searchText: String): Flow<List<StationWithLines>>

    @Query("SELECT * FROM stations JOIN favorites ON stations.stationIdx = favorites.stationIdx")
    fun getFavoriteStationsFlow(): Flow<List<StationWithLines>>

    @Query("SELECT COUNT(*) FROM stations")
    suspend fun getStationsSize(): Int

    @Insert
    suspend fun insertStations(stations: List<Station>)

    @Insert
    suspend fun insertLines(lines: List<Line>)

    @Insert
    suspend fun insertStationLineCrossRefs(refs: List<StationLineCrossRef>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE stationIdx = :stationIdx")
    suspend fun deleteFavorite(stationIdx: Int)

    @Query("DELETE FROM favorites")
    suspend fun clearFavorites()
}