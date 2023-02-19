package io.github.keddnyo.quicktag.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.keddnyo.quicktag.domain.view.navigation.BoardRuleNavHost
import io.github.keddnyo.quicktag.presentation.ui.theme.QuickTagTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickTagTheme {
                BoardRuleNavHost()
            }
        }
    }
}