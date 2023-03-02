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
                    ArticleScreen()
                }
            }
        }
    }
}

@Composable
fun ArticleScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Header()
            Body()
        }
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = com.serkancay.knowcean.R.string.next_button))
        }
    }

}

@Composable
fun Header(
    title: String = Dummy.TITLE,
    imageUrl: String = Dummy.IMAGE_URL,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(250.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.primary
                        )
                    )
                )
        )
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Composable
fun Body(body: String = Dummy.BODY_LARGE, modifier: Modifier = Modifier) {
    Text(text = body, modifier = modifier.padding(16.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KnowceanTheme {
        Header()
    }
}