package io.github.keddnyo.quicktag.presentation.component.pop_up

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.keddnyo.quicktag.R

@Composable
fun IconArrowBack() {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = stringResource(id = R.string.navigate_up)
    )
}