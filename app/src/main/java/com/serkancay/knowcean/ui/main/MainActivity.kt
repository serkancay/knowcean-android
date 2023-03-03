package com.serkancay.knowcean.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.serkancay.knowcean.constant.Dummy
import com.serkancay.knowcean.network.model.Page
import com.serkancay.knowcean.network.model.Response
import com.serkancay.knowcean.ui.screen.ArticleScreen
import com.serkancay.knowcean.ui.screen.LoadingScreen
import com.serkancay.knowcean.ui.theme.KnowceanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRandomPage()
        setContent {
            KnowceanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val uiState = viewModel.pageStateFlow.collectAsState()
                    when (val state = uiState.value) {
                        is Response.Loading -> {
                            LoadingScreen(stringResource(id = com.serkancay.knowcean.R.string.article_loading_message))
                        }
                        is Response.Failure -> { /* TODO show error screen */
                        }
                        is Response.Success -> {
                            ArticleScreen(state.data) {
                                viewModel.getRandomPage()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KnowceanTheme {
        ArticleScreen()
    }
}