package com.example.colorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.colorapp.viewmodel.ColorViewModel
import com.example.colorapp.viewmodel.ColorViewModelFactory


class MainActivity : ComponentActivity() {
    private val viewModel: ColorViewModel by viewModels {
        ColorViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorScreen(viewModel)
        }
    }
}

