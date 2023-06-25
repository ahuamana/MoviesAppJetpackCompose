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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahuaman.moviesapp.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ErrorScreen() {
    val lottieLoading by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error_lottie))
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
fun ErrorScreenPrev() {
    ErrorScreen()
}

@Composable
fun CustomErrorScreenSomethingHappens(
    modifier: Modifier = Modifier,
){
    CustomEmptyStateScreen(
        modifier = modifier,
        title = stringResource(id = R.string.empty_screen_title_error_something_went_wrong),
        //Algo pasó, por favor intenta de nuevo
        description = stringResource(id = R.string.empty_screen_description_error_something_went_wrong),
        image = R.drawable.background_something_wrong
    )
}

//no internet
@Composable
fun CustomNoInternetConnectionScreen(
    modifier: Modifier = Modifier,
){
    CustomEmptyStateScreen(
        modifier = modifier,
        title = stringResource(id = R.string.empty_screen_title_no_internet),
        //Algo pasó, por favor intenta de nuevo
        description = stringResource(id = R.string.empty_screen_descripcion_no_internet),
        image = R.drawable.background_no_internet_connection
    )
}

//emptysearch
@Composable
fun CustomEmptySearchScreen(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.empty_screen_title_not_found_results),
    description: String = stringResource(id = R.string.empty_screen_description_no_results, "busqueda")
){
    CustomEmptyStateScreen(
        modifier = modifier,
        title = title,
        description = description,
        image = R.drawable.background_empty_state
    )
}

@Preview
@Composable
fun ErrorScreen2Prev() {
    CustomErrorScreenSomethingHappens()
}


