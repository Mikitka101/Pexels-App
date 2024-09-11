package com.nikitayasiulevich.pexelsapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nikitayasiulevich.pexelsapp.ui.theme.PexelsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PexelsAppTheme {
                MainScreen(application)
            }
        }
    }
}