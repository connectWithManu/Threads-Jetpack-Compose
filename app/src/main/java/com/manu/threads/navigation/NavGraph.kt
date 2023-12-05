package com.manu.threads.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manu.threads.screens.AddThreads
import com.manu.threads.screens.BottomNav
import com.manu.threads.screens.Home
import com.manu.threads.screens.LogIn
import com.manu.threads.screens.Notification
import com.manu.threads.screens.Profile
import com.manu.threads.screens.Register
import com.manu.threads.screens.Search
import com.manu.threads.screens.Splash

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Splash.routes) {
        composable(route = Screens.Splash.routes) {
            Splash(navController)
        }
        composable(route = Screens.BottomNav.routes) {
            BottomNav(navController)
        }
        composable(route = Screens.Notification.routes) {
            Notification()
        }
        composable(route = Screens.Profile.routes) {
            Profile(navController)
        }
        composable(route = Screens.Search.routes) {
            Search()
        }
        composable(route = Screens.AddThreads.routes) {
            AddThreads()
        }
        composable(route = Screens.Home.routes) {
            Home()
        }
        composable(route = Screens.Login.routes) {
            LogIn(navController)
        }
        composable(route = Screens.Register.routes) {
           Register(navController)
        }
    }
}