package com.manu.threads.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.manu.threads.navigation.Screens
import com.manu.threads.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogIn(navController: NavHostController) {

    val authViewModel: AuthViewModel = viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(firebaseUser) {
        if(firebaseUser != null) {
            navController.navigate(Screens.BottomNav.routes) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }
        Text(text = "Login", fontSize = 24.sp)
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        OutlinedTextField(
            value = pass,
            onValueChange = {pass = it},
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Password")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )
        Spacer(modifier = Modifier.size(20.dp))
        ElevatedButton(
            onClick = {
                     if(email.isEmpty() || pass.isEmpty()) {
                         Toast.makeText(context, "Please enter valid details", Toast.LENGTH_SHORT).show()
                     } else {
                         authViewModel.login(email, pass, context)
                     }
                      },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.size(10.dp))
        TextButton(
            onClick = { navController.navigate(Screens.Register.routes) },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "New User ? Create Account")
        }


    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginPrev() {
    //LogIn(navController)
}