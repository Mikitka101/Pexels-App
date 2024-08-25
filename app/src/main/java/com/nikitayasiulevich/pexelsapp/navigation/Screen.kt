package com.nikitayasiulevich.pexelsapp.navigation

import android.net.Uri
import com.google.gson.Gson
import com.nikitayasiulevich.pexelsapp.domain.Photo

sealed class Screen(
    val route: String
) {

    data object Home: Screen(ROUTE_HOME)
    data object HomeGraph: Screen(ROUTE_HOME_GRAPH)
    data object Bookmarks: Screen(ROUTE_BOOKMARKS)
    data object BookmarksGraph: Screen(ROUTE_BOOKMARKS_GRAPH)
    data object Details: Screen(ROUTE_DETAILS) {

        private const val ROUTE_FOR_ARGS = "details"

        fun getRouteWithArgs(photo: Photo): String {
            val gson = Gson().toJson(photo)
            return "$ROUTE_FOR_ARGS/${Uri.encode(gson)}"
        }
    }

    companion object {

        const val KEY_PHOTO = "photo"

        const val ROUTE_HOME = "home"
        const val ROUTE_HOME_GRAPH = "home_graph"
        const val ROUTE_BOOKMARKS = "bookmarks"
        const val ROUTE_BOOKMARKS_GRAPH = "bookmarks_graph"
        const val ROUTE_DETAILS = "details/{${KEY_PHOTO}}"
    }
}