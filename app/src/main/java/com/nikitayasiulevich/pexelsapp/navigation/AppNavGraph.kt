package com.nikitayasiulevich.pexelsapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nikitayasiulevich.pexelsapp.domain.Photo

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    bookmarksScreenContent: @Composable () -> Unit,
    detailsScreenForHomePageContent: @Composable (Photo) -> Unit,
    detailsScreenForBookmarksPageContent: @Composable (Photo) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeGraph.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(400)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(400)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(400)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(400)
            )
        }
    ) {
        homeScreenNavGraph(
            homeScreenContent = homeScreenContent,
            detailsScreenContent = detailsScreenForHomePageContent
        )
        bookmarksScreenNavGraph(
            bookmarksScreenContent = bookmarksScreenContent,
            detailsScreenContent = detailsScreenForBookmarksPageContent
        )
    }
}