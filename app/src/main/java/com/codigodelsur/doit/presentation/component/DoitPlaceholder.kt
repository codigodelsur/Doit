package com.codigodelsur.doit.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codigodelsur.doit.R
import com.codigodelsur.doit.presentation.theme.DoitTheme

@Composable
fun DoitPlaceholder(
    painter: Painter,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled),
                modifier = Modifier.padding(bottom = 12.dp),
            )
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled),
            )
            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled),
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DoitPlaceholderPreview() {
    DoitTheme {
        DoitPlaceholder(
            painter = painterResource(id = R.drawable.ic_calendar),
            title = stringResource(id = R.string.home_placeholder_title),
            description = stringResource(id = R.string.home_placeholder_description),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        )
    }
}
