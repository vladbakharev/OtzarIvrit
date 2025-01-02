package com.vladbakharev.otzarivrit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vladbakharev.otzarivrit.navigation.NavigationStack
import com.vladbakharev.otzarivrit.ui.theme.OtzarIvritTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtzarIvritTheme {
                NavigationStack()
            }
        }
    }
}