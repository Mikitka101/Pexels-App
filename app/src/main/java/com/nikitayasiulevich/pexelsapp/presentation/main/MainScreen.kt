package com.nikitayasiulevich.pexelsapp.presentation.main

import android.app.Application
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nikitayasiulevich.pexelsapp.navigation.AppNavGraph
import com.nikitayasiulevich.pexelsapp.navigation.rememberNavigationState
import com.nikitayasiulevich.pexelsapp.presentation.bookmarks.BookmarksScreen
import com.nikitayasiulevich.pexelsapp.presentation.details.DetailsScreen
import com.nikitayasiulevich.pexelsapp.presentation.home.HomeScreen
import com.nikitayasiulevich.pexelsapp.ui.theme.Gray
import com.nikitayasiulevich.pexelsapp.ui.theme.Red40
import com.nikitayasiulevich.pexelsapp.ui.theme.WhiteEE
import com.nikitayasiulevich.pexelsapp.ui.theme.WhiteFF

@Composable
fun MainScreen(
    application: Application
) {

    val navigationState = rememberNavigationState()

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        containerColor = WhiteEE,
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomAppBar(
                    modifier = Modifier.height(56.dp),
                    containerColor = WhiteFF,
                    tonalElevation = 8.dp
                ) {
                    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                    val items = listOf(
                        NavigationItem.Home,
                        NavigationItem.Bookmarks
                    )

                    items.forEach { item ->
                        val selected = navBackStackEntry?.destination?.hierarchy?.any {
                            it.route == item.screen.route
                        } ?: false
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navigationState.navigateTo(item.screen.route)
                                }
                            },
                            icon = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(top = 0.dp, bottom = 8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Spacer(
                                        modifier = Modifier
                                            .width(50.dp)
                                            .height(3.dp)
                                            .background(color = if (selected) Red40 else Color.White)
                                    )
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = stringResource(id = item.titleResId),
                                        modifier = Modifier
                                            .padding(bottom = 4.dp)
                                            .size(30.dp)
                                    )
                                }
                            },
                            colors = NavigationBarItemColors(
                                selectedIconColor = Red40,
                                unselectedIconColor = Gray,
                                selectedIndicatorColor = Color.Transparent,
                                selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                                disabledIconColor = MaterialTheme.colorScheme.onSecondary,
                                disabledTextColor = MaterialTheme.colorScheme.onSecondary
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {
                bottomBarState.value = true
                HomeScreen(
                    paddingValues = paddingValues,
                    onPhotoClickListener = { photo ->
                        navigationState.navigateToDetails(photo)
                    }
                )
            },
            bookmarksScreenContent = {
                bottomBarState.value = true
                BookmarksScreen(
                    paddingValues = paddingValues,
                    onPhotoClickListener = { photo ->
                        navigationState.navigateToDetails(photo)
                    }
                )
            },
            detailsScreenForHomePageContent = { photo ->
                bottomBarState.value = false
                DetailsScreen(
                    photo = photo,
                    application = application,
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    }
                )
            },
            detailsScreenForBookmarksPageContent = { photo ->
                bottomBarState.value = false
                DetailsScreen(
                    photo = photo,
                    application = application,
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    }
                )
            }
        )
    }
}