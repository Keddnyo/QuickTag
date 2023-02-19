package io.github.keddnyo.quicktag.presentation.screen.type

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import io.github.keddnyo.quicktag.R
import io.github.keddnyo.quicktag.domain.viewmodel.BoardRuleViewModel
import io.github.keddnyo.quicktag.presentation.component.pop_up.IconArrowBack
import io.github.keddnyo.quicktag.presentation.component.tag.TagRow
import io.github.keddnyo.quicktag.presentation.component.tag.TagVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeScreen(navController: NavHostController, viewModel: BoardRuleViewModel) {

    val clipboardManager = LocalClipboardManager.current
    val haptic = LocalHapticFeedback.current

    val rule = viewModel.currentBoardRule

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.select_tag_type)
                )
            }, navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    IconArrowBack()
                }
            })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn {
                item {
                    TagRow(
                        tag = TagVariant.CUR,
                        content = rule.contentPreview,
                        onClick = {
                            clipboardManager.copyTag(TagVariant.CUR, rule.content)
                            navController.popBackStack()
                        },
                        onLongClick = {
                            clipboardManager.copyTag(TagVariant.CUR, rule.content)
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            navController.popBackStack()
                        }
                    )
                }
                item {
                    TagRow(
                        tag = TagVariant.MOD,
                        content = rule.contentPreview,
                        onClick = {
                            clipboardManager.copyTag(TagVariant.MOD, rule.content)
                            navController.popBackStack()
                        },
                        onLongClick = {
                            clipboardManager.copyTag(TagVariant.MOD, rule.content)
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            navController.popBackStack()
                        }
                    )
                }
                item {
                    TagRow(
                        tag = TagVariant.EX,
                        content = rule.contentPreview,
                        onClick = {
                            clipboardManager.copyTag(TagVariant.EX, rule.content)
                            navController.popBackStack()
                        },
                        onLongClick = {
                            clipboardManager.copyTag(TagVariant.EX, rule.content)
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

private fun ClipboardManager.copyTag(tag: TagVariant, content: String) {
    val type = tag.tagWrapper
    val text = "[$type]$content[/$type]"
    setText(AnnotatedString(text))
}