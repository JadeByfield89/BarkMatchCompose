package com.demo.barkmatchcompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage


@Composable
fun BreedSearchResultsView(matchResult: com.demo.barkmatchcompose.model.MatchResult, navController: NavController) {
FullScreenTopBar(title = "Search Results", navController) {
    Box(modifier = Modifier.background(Color.White)) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(matchResult.messageList) { url ->
                ImageCell(url)
            }
        }
    }
}
}

@Composable
fun ImageCell(url: String) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

