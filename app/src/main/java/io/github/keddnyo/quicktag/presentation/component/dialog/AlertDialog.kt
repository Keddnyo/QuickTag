package io.github.keddnyo.quicktag.presentation.component.dialog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    title: String,
    text: String
) {
    androidx.compose.material3.AlertDialog(onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        confirmButton()
    }, dismissButton = {
        dismissButton()
    }, title = {
        Text(
            text = title
        )
    }, text = {
        Text(
            text = text
        )
    })
}