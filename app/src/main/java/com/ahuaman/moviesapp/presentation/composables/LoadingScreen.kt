package com.ahuaman.moviesapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahuaman.moviesapp.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingScreen() {
    val lottieLoading by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_amazing))
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = lottieLoading,
                modifier = Modifier.
                fillMaxWidth(1f),
                iterations = LottieConstants.IterateForever)
        }
    }
}


@Preview
@Composable
fun LoadingScreenPrev() {
    LoadingScreen()
}


