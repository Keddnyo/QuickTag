package io.github.keddnyo.quicktag.presentation.component.tag

import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TagRow(
    tag: TagVariant = TagVariant.CUR, content: String, onClick: () -> Unit, onLongClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    val background = if (isSystemInDarkTheme()) {
        tag.color.darkColor
    } else {
        tag.color.lightColor
    }

    Row(
        modifier = Modifier
            .padding(15.dp)
            .border(width = 1.dp, color = background)
            .defaultMinSize(minHeight = 40.dp)
            .height(IntrinsicSize.Max)
            .combinedClickable(interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = {
                    onClick()
                },
                onLongClick = {
                    onLongClick()
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                })
    ) {
        Box(
            modifier = Modifier
                .background(background)
                .padding(vertical = 5.dp, horizontal = 15.dp)
                .width(width = 22.dp)
                .fillMaxHeight(), contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = tag.symbol, color = Color.White, fontSize = 28.sp
            )
        }
        AndroidView(
            factory = {
                TextView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    text = HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY)
                }
            }, modifier = Modifier
                .padding(5.dp)
                .align(Alignment.CenterVertically)
        )
    }
}