package io.github.keddnyo.quicktag.presentation.screen.type

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import io.github.keddnyo.quicktag.R
import io.github.keddnyo.quicktag.domain.viewmodel.BoardRuleViewModel
import io.github.keddnyo.quicktag.presentation.component.pop_up.IconArrowBack
import io.github.keddnyo.quicktag.presentation.component.tag.TagRow
import io.github.keddnyo.quicktag.presentation.component.tag.TagVariant
import io.github.keddnyo.quicktag.utils.Display
import io.github.keddnyo.quicktag.utils.openFourPDA

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeScreen(navController: NavHostController, viewModel: BoardRuleViewModel) {

    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    val content = rememberSaveable { viewModel.currentBoardRule.content }
    val contentPreview = rememberSaveable { viewModel.currentBoardRule.contentPreview }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = stringResource(id = R.string.select_tag_type)
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                IconArrowBack()
            }
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn {
                item {
                    TagRow(tag = null, content = contentPreview, onClick = {
                        clipboardManager.copyTag(context, null, content)
                    }, onLongClick = {
                        clipboardManager.copyTag(context, null, content, true)
                    })
                }
                item {
                    TagRow(tag = TagVariant.CUR, content = contentPreview, onClick = {
                        clipboardManager.copyTag(context, TagVariant.CUR, content)
                    }, onLongClick = {
                        clipboardManager.copyTag(context, TagVariant.CUR, content, true)
                    })
                }
                item {
                    TagRow(tag = TagVariant.MOD, content = contentPreview, onClick = {
                        clipboardManager.copyTag(context, TagVariant.MOD, content)
                    }, onLongClick = {
                        clipboardManager.copyTag(context, TagVariant.MOD, content, true)
                    })
                }
                item {
                    TagRow(tag = TagVariant.EX, content = contentPreview, onClick = {
                        clipboardManager.copyTag(context, TagVariant.EX, content)
                    }, onLongClick = {
                        clipboardManager.copyTag(context, TagVariant.EX, content, true)
                    })
                }
            }
        }
    }
}

private fun ClipboardManager.copyTag(
    context: Context, tag: TagVariant?, content: String, showForum: Boolean = false
) {
    if (tag != null) {
        val type = tag.tagWrapper
        val text = "[$type]\n$content\n[/$type]"
        setText(AnnotatedString(text))
    } else {
        setText(AnnotatedString(content))
    }

    Display().showToast(context, context.getString(R.string.rule_copied_to_clipboard))

    if (showForum) {
        context.openFourPDA()
    }
}