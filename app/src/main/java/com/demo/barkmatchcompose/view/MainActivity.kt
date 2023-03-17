package com.demo.barkmatchcompose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.demo.barkmatchcompose.ui.theme.BarkMatchComposeTheme
import com.demo.barkmatchcompose.view.BreedSearchView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
               BreedSearchView()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}