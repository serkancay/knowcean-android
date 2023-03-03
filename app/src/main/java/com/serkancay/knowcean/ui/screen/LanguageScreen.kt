package com.serkancay.knowcean.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serkancay.knowcean.R
import com.serkancay.knowcean.constant.Language

@Composable
fun LanguageScreen(
    languages: List<Language>,
    modifier: Modifier = Modifier,
    onSelect: (String) -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_ocean),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary, blendMode = BlendMode.Multiply),
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier.matchParentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
            LanguageList(languages = languages, modifier = Modifier.weight(1f)) {
                onSelect.invoke(it)
            }
        }
    }
}

@Composable
fun LanguageList(
    languages: List<Language>,
    modifier: Modifier = Modifier,
    onSelect: (String) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.choose_content_language),
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
        Column {
            languages.forEach {
                Button(onClick = { onSelect.invoke(it.code) }) {
                    Image(
                        painter = painterResource(id = it.iconRes),
                        contentDescription = it.title,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = it.title)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}