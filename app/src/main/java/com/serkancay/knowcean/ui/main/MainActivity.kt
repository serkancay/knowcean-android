package com.serkancay.knowcean.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.serkancay.knowcean.R
import com.serkancay.knowcean.network.model.Response
import com.serkancay.knowcean.ui.screen.ArticleScreen
import com.serkancay.knowcean.ui.screen.FailureScreen
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
                            LoadingScreen(stringResource(id = R.string.article_loading_message))
                        }
                        is Response.Failure -> {
                            FailureScreen(
                                message = state.exception?.message,
                                buttonText = stringResource(id = R.string.retry_button)
                            ) {
                                viewModel.getRandomPage()
                            }
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