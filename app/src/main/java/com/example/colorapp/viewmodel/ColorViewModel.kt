package com.example.colorapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.colorapp.database.AppDatabase
import com.example.colorapp.database.ColorDao
import com.example.colorapp.database.ColorEntity
import com.example.colorapp.repository.ColorRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class ColorViewModel(application: Application) : AndroidViewModel(application) {

    private val colorDao: ColorDao = AppDatabase.getDatabase(application).colorDao()
    private val firebaseDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val repository: ColorRepository = ColorRepository(colorDao, firebaseDatabase)

    val colors: LiveData<List<ColorEntity>> = repository.getAllColors()

    fun addColor() {
        viewModelScope.launch {
            val randomColor = String.format("#%06X", (0xFFFFFF and (Math.random() * 0xFFFFFF).toInt()))
            val timestamp = System.currentTimeMillis()
            val colorEntity = ColorEntity(color = randomColor, time = timestamp)
            repository.addColor(colorEntity)
        }
    }

    fun syncColors() {
        viewModelScope.launch {
            repository.syncColors()
        }
    }
}
