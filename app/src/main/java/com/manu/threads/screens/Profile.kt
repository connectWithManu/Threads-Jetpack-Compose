package com.manu.threads.screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.manu.threads.navigation.Screens
import com.manu.threads.viewmodel.AuthViewModel


@Composable
fun Profile(navController: NavHostController) {

    val authViewModel: AuthViewModel = viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState()

    LaunchedEffect(firebaseUser) {
        if(firebaseUser == null) {
            navController.navigate(Screens.Login.routes) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }
    Text(text = "profile", modifier = Modifier.clickable {
        authViewModel.logOut()
    })
}