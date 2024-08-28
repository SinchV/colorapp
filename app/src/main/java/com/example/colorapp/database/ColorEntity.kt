package com.example.colorapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colors")
data class ColorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val color: String,
    val time: Long
)