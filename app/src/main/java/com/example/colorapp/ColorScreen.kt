package com.example.colorapp

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.filled.Sync
import androidx.compose.runtime.getValue

import androidx.compose.runtime.livedata.observeAsState
import com.example.colorapp.database.ColorEntity
import com.example.colorapp.viewmodel.ColorViewModel


fun parseColor(colorString: String): ComposeColor {
    return try {
        ComposeColor(AndroidColor.parseColor(colorString))
    } catch (e: IllegalArgumentException) {
        ComposeColor.Gray // Default color if parsing fails
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorScreen(viewModel: ColorViewModel = viewModel()) {
    val colors by viewModel.colors.observeAsState(emptyList())
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Colors") }, actions = {
                IconButton(onClick = { viewModel.syncColors() }) {
                    Icon(Icons.Default.Sync, contentDescription = "Sync")
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.addColor() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Color")
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(colors) { colorEntity ->
                ColorItem(colorEntity)
            }
        }
    }
}

@Composable
fun ColorItem(colorEntity: ColorEntity) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(parseColor(colorEntity.color)) // Set background color here
    ) {
        Text(
            text = "Color: ${colorEntity.color}, Time: ${colorEntity.time}",
            modifier = Modifier.padding(16.dp)
        )
    }
}
