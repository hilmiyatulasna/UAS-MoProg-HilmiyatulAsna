package com.example.tugasandroid.ui.route.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.tugasandroid.R
import kotlinx.coroutines.delay
import org.jetbrains.annotations.Async

@Composable
fun SplashRoute(
    toHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(1000L)
        toHome()
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "splash image"
        )
    }
}