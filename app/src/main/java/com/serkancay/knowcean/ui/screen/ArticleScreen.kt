package com.serkancay.knowcean.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.serkancay.knowcean.R
import com.serkancay.knowcean.constant.Dummy
import com.serkancay.knowcean.network.model.Page
import com.serkancay.knowcean.network.model.Thumbnail

@Composable
fun ArticleScreen(
    page: Page = Page(
        title = Dummy.TITLE,
        thumbnail = Thumbnail(Dummy.IMAGE_URL),
        extract = Dummy.BODY_LARGE
    )
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Header(title = page.title, imageUrl = page.thumbnail.source)
            Body(body = page.extract)
        }
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.next_button))
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