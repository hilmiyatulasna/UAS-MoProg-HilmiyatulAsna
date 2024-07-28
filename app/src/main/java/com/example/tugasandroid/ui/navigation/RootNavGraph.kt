package com.example.tugasandroid.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugasandroid.ui.route.detail.DetailRoute
import com.example.tugasandroid.ui.route.home.HomeRoute
import com.example.tugasandroid.ui.route.profile.ProfileRoute
import com.example.tugasandroid.ui.route.search.SearchRoute
import com.example.tugasandroid.ui.route.splash.SplashRoute

@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val bottomBarItem = listOf(
        BottomBarItem(
            route = Screen.Home.route,
            label = "Home",
            icon = Icons.Filled.Home
        ),
        BottomBarItem(
            route = Screen.Search.route,
            label = "Search",
            icon = Icons.Filled.Search
        ),
        BottomBarItem(
            route = Screen.Profile.route,
            label = "Profile",
            icon = Icons.Filled.Person
        )
    )

    Scaffold(
        bottomBar = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            if (bottomBarItem.map { it.route }.contains(currentRoute)) {
                NavigationBar {
                    bottomBarItem.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = { navController.navigate(item.route) },
                            icon = { Icon(item.icon, contentDescription = "navigation icon") },
                            label = { Text(text = item.label) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Screen.Splash.route
        ) {
            composable(Screen.Splash.route) {
                SplashRoute(
                    toHome = { navController.navigate(Screen.Home.route) },
                )
            }
            composable(Screen.Home.route) {
                HomeRoute(
                    toDetail = { navController.navigate(Screen.Detail.createRoute(it)) }
                )
            }
            composable(Screen.Search.route) {
                SearchRoute(
                    toDetail = { navController.navigate(Screen.Detail.createRoute(it)) }
                )
            }
            composable(Screen.Profile.route) {
                ProfileRoute(toDetail = { navController.navigate(Screen.Detail.createRoute(it)) })
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.IntType
                    }
                )
            ) {
                DetailRoute(
                    onBackStack = { navController.navigateUp() }
                )
            }
        }
    }
}