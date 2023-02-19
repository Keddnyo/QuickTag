package io.github.keddnyo.quicktag.domain.view.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.keddnyo.quicktag.domain.viewmodel.BoardRuleViewModel
import io.github.keddnyo.quicktag.presentation.screen.edit.EditScreen
import io.github.keddnyo.quicktag.presentation.screen.main.MainScreen
import io.github.keddnyo.quicktag.presentation.screen.type.TypeScreen

@Composable
fun BoardRuleNavHost() {

    val context = LocalContext.current

    val navController = rememberNavController()
    val viewModel = BoardRuleViewModel(context.applicationContext as Application)

    val mainScreen = BoardRuleNavRoute.BoardRuleMainScreen.route
    val editScreen = BoardRuleNavRoute.BoardRuleEditScreen.route
    val typeScreen = BoardRuleNavRoute.BoardRuleTypeScreen.route

    NavHost(navController, mainScreen) {
        composable(mainScreen) {
            MainScreen(navController, viewModel)
        }
        composable(editScreen) {
            EditScreen(navController, viewModel)
        }
        composable(typeScreen) {
            TypeScreen(navController, viewModel)
        }
    }

}