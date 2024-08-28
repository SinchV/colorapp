package com.example.colorapp.database
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ColorDao {
    @Query("SELECT * FROM colors")
    fun getAllColors(): LiveData<List<ColorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColor(color: ColorEntity)

    @Query("DELETE FROM colors WHERE id IN (:colorIds)")
    suspend fun deleteColorsById(colorIds: List<Int>)
}
