package com.demo.barkmatchcompose.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.barkmatchcompose.model.MatchResult
import com.demo.barkmatchcompose.viewmodel.BreedSearchViewModel
import kotlinx.coroutines.flow.*

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BreedSearchView() {
    val navController = rememberNavController()
    var searchQuery by remember { mutableStateOf("") }
    val viewModel: BreedSearchViewModel = viewModel()
    val matchResultFlow = viewModel.matchResults.asFlow()
    val matchResultsState = matchResultFlow.collectAsState(
        MatchResult(
        emptyList()
    )
    )

    FullScreenTopBar(title = "BarkMatch", navController = navController) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            OutlinedTextField(value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Enter Breed Here") }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.searchDogsByBreed(searchQuery)
                searchQuery = ""
                navController.navigate("results") {
                    launchSingleTop = true
                }
            }) {
                Text("Search")
            }

        }

    }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // Home screen
        }
        composable(
            "results"
        ) {
            val matchResult = matchResultsState.value
            BreedSearchResultsView(matchResult ?: MatchResult(emptyList()), navController)
        }
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FullScreenTopBar(title: String, navController: NavController, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        content()
    }
}

