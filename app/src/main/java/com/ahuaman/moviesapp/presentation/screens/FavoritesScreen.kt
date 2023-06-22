package com.ahuaman.moviesapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoritesScreen(
    onClickNavigateToDetails: () -> Unit
) {
    Text(text = "Favorites Screen", modifier = Modifier.clickable { onClickNavigateToDetails() })
}