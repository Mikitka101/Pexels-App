package com.nikitayasiulevich.pexelsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.nikitayasiulevich.pexelsapp.domain.Photo
import com.nikitayasiulevich.pexelsapp.navigation.Screen.Companion.KEY_PHOTO

fun NavGraphBuilder.homeScreenNavGraph(
    homeScreenContent: @Composable () -> Unit,
    detailsScreenContent: @Composable (Photo) -> Unit
) {
    navigation(
        startDestination = Screen.Home.route,
        route = Screen.HomeGraph.route
    ) {
        composable(route = Screen.Home.route) {
            homeScreenContent()
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(KEY_PHOTO) {
                    type = NavType.StringType
                }
            )
        ) {
            val photoJson = it.arguments?.getString(KEY_PHOTO) ?: ""
            val photo = Gson().fromJson(photoJson, Photo::class.java)
            detailsScreenContent(photo)
        }
    }
}