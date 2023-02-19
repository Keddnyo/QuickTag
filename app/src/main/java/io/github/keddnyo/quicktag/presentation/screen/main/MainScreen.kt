package io.github.keddnyo.quicktag.presentation.screen.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import io.github.keddnyo.quicktag.R
import io.github.keddnyo.quicktag.common.Constants
import io.github.keddnyo.quicktag.domain.model.BoardRule
import io.github.keddnyo.quicktag.domain.view.navigation.BoardRuleNavRoute
import io.github.keddnyo.quicktag.domain.viewmodel.BoardRuleViewModel
import io.github.keddnyo.quicktag.presentation.component.dialog.AlertDialog
import io.github.keddnyo.quicktag.presentation.component.tag.TagRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, viewModel: BoardRuleViewModel) {

    var openDialog by remember { mutableStateOf(false) }
    val githubPage = Constants.GITHUB_REPOSITORY_URL
    val fourPdaPage = Constants.FOUR_PDA_PROFILE_URL

    val context = LocalContext.current

    val boardRules = viewModel.getBoardRules().observeAsState(initial = listOf()).value

    val editScreen = BoardRuleNavRoute.BoardRuleEditScreen.route
    val typeScreen = BoardRuleNavRoute.BoardRuleTypeScreen.route

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        }, navigationIcon = {
            IconButton(onClick = {
                openDialog = true
            }) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = stringResource(id = R.string.github)
                )
            }
        }, actions = {
            IconButton(onClick = {
                viewModel.apply {
                    currentBoardRule = BoardRule()
                    isBoardRuleExists = false
                }
                navigateToScreen(navController, editScreen)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.create_board_rule)
                )
            }
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn {
                items(boardRules) { rule ->
                    TagRow(content = rule.contentPreview, onClick = {
                        viewModel.apply {
                            currentBoardRule = rule
                            isBoardRuleExists = true
                        }
                        navigateToScreen(navController, typeScreen)
                    }, onLongClick = {
                        viewModel.apply {
                            currentBoardRule = rule
                            isBoardRuleExists = true
                        }
                        navigateToScreen(navController, editScreen)
                    })
                }
            }
        }
    }

    if (openDialog) {
        AlertDialog(onDismissRequest = {
            openDialog = false
        },
            confirmButton = {
                Button(onClick = {
                    context.openWebPage(githubPage)
                }) {
                    Text(
                        text = stringResource(id = R.string.github)
                    )
                }
            },
            dismissButton = {
                Button(onClick = {
                    context.openWebPage(fourPdaPage)
                }) {
                    Text(
                        text = stringResource(id = R.string.four_pda)
                    )
                }
            },
            title = stringResource(id = R.string.about),
            text = stringResource(id = R.string.about_summary)
        )
    }
}

private fun Context.openWebPage(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}

private fun navigateToScreen(navController: NavHostController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}