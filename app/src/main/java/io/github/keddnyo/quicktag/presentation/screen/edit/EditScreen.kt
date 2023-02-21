package io.github.keddnyo.quicktag.presentation.screen.edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.keddnyo.quicktag.R
import io.github.keddnyo.quicktag.domain.model.BoardRule
import io.github.keddnyo.quicktag.domain.viewmodel.BoardRuleViewModel
import io.github.keddnyo.quicktag.presentation.component.pop_up.IconArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(navController: NavHostController, viewModel: BoardRuleViewModel) {

    val rule = viewModel.currentBoardRule
    val isRuleExist = rule != BoardRule()

    var content by rememberSaveable { mutableStateOf(rule.content) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = stringResource(id = R.string.editor)
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                IconArrowBack()
            }
        }, actions = {
            if (content.isNotBlank()) {
                IconButton(onClick = {
                    val newRule = BoardRule(
                        id = rule.id, content = content
                    )
                    viewModel.modifyDatabase(rule, newRule) {
                        navController.popBackStack()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = stringResource(id = R.string.done_edit_rule)
                    )
                }
            }
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            TextField(value = content,
                onValueChange = {
                    content = it
                },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                singleLine = false,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.editor_placeholder)
                    )
                })
        }
    }
}

private fun BoardRuleViewModel.modifyDatabase(
    rule: BoardRule, newRule: BoardRule, onSuccess: () -> Unit
) {
    if (rule != BoardRule()) {
        updateBoardRule(newRule) {
            onSuccess()
        }
    } else {
        createBoardRule(newRule) {
            onSuccess()
        }
    }
}