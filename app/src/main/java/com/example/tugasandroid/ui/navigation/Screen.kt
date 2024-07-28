package com.example.tugasandroid.ui.navigation

sealed class Screen(val route: String) {
    data object Splash: Screen(route = "splash")
    data object Home : Screen(route = "home")
    data object Search: Screen(route = "search")
    data object Detail : Screen(route = "detail/{movieId}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
    data object Profile: Screen(route = "profile")
}