package io.github.keddnyo.quicktag.domain.view.navigation

sealed class BoardRuleNavRoute(val route: String) {
    object BoardRuleMainScreen : BoardRuleNavRoute(route = "main_screen")
    object BoardRuleEditScreen : BoardRuleNavRoute(route = "edit_screen")
    object BoardRuleTypeScreen : BoardRuleNavRoute(route = "type_screen")
}