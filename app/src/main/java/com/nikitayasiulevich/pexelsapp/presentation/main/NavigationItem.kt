package com.nikitayasiulevich.pexelsapp.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.nikitayasiulevich.pexelsapp.R
import com.nikitayasiulevich.pexelsapp.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {
    data object Home: NavigationItem(
        screen = Screen.HomeGraph,
        titleResId = R.string.navigation_item_home,
        icon = Icons.Outlined.Home
    )
    data object Bookmarks: NavigationItem(
        screen = Screen.BookmarksGraph,
        titleResId = R.string.navigation_item_bookmarks,
        icon = Icons.Outlined.FavoriteBorder
    )
}