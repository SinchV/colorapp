package com.example.colorapp.repository

import androidx.lifecycle.LiveData
import com.example.colorapp.database.ColorDao
import com.example.colorapp.database.ColorEntity
import com.google.firebase.database.DatabaseReference

class ColorRepository(private val colorDao: ColorDao, private val firebaseDatabase: DatabaseReference) {

    fun getAllColors(): LiveData<List<ColorEntity>> = colorDao.getAllColors()

    suspend fun addColor(colorEntity: ColorEntity) {
        colorDao.insertColor(colorEntity)
        val colorId = colorEntity.id
        firebaseDatabase.child("colors").child(colorId.toString()).setValue(colorEntity)
    }

    suspend fun syncColors() {
        val colors = colorDao.getAllColors().value ?: return
        colors.forEach {
            firebaseDatabase.child("colors").child(it.id.toString()).setValue(it)
        }
        colorDao.deleteColorsById(colors.map { it.id })
    }
}