package com.manu.threads.navigation

sealed class Screens(val routes: String) {
    object Home: Screens("Home")
    object Notification: Screens("Notification")
    object Search: Screens("Search")
    object Profile: Screens("Profile")
    object Splash: Screens("Splash")
    object AddThreads: Screens("AddThreads")
    object BottomNav: Screens("BottomNav")

    object Login: Screens("Login")
    object Register: Screens("Register")
}