package com.nikitayasiulevich.pexelsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nikitayasiulevich.pexelsapp.domain.Photo
import com.nikitayasiulevich.pexelsapp.navigation.AppNavGraph
import com.nikitayasiulevich.pexelsapp.navigation.Screen
import com.nikitayasiulevich.pexelsapp.navigation.rememberNavigationState
import com.nikitayasiulevich.pexelsapp.ui.theme.Grey
import com.nikitayasiulevich.pexelsapp.ui.theme.Red40
import com.nikitayasiulevich.pexelsapp.ui.theme.WhiteEE
import com.nikitayasiulevich.pexelsapp.ui.theme.WhiteFF

@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    Scaffold(
        containerColor = WhiteEE,
        bottomBar = {
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
                            if(!selected){
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = stringResource(id = item.titleResId)
                            )
                        },
                        colors = NavigationBarItemColors(
                            selectedIconColor = Red40,
                            unselectedIconColor = Grey,
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
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {
                HomeScreen(
                    paddingValues = paddingValues,
                    onPhotoClickListener = { photo ->
                        navigationState.navigateToDetails(photo)
                    }
                )
            },
            bookmarksScreenContent = {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Green),
                    text = "bookmarks screen"
                )
            },
            detailsScreenContent = { photo ->
                DetailsScreen(
                    photo = photo,
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    }
                )
            }
        )
    }
}