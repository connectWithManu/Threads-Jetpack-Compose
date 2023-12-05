package com.manu.threads.screens

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.manu.threads.R
import com.manu.threads.navigation.Screens
import com.manu.threads.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var email by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var bio by remember { mutableStateOf("") }
        var userName by remember { mutableStateOf("") }
        var imgUri by remember { mutableStateOf<Uri?>(null) }

        val authViewModel: AuthViewModel = viewModel()
        val firebaseUser by authViewModel.firebaseUser.observeAsState(null)
        val context = LocalContext.current
        val permissionToRequest = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()) {
            uri: Uri ? ->

            imgUri = uri
        }


        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()) {

            isGranted: Boolean ->
            if(isGranted) {

            } else {

            }

        }

        LaunchedEffect(firebaseUser) {
            if(firebaseUser != null) {
                navController.navigate(Screens.BottomNav.routes) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        }

        Text(text = "Register Account", fontSize = 24.sp)
        Image(painter = if(imgUri ==null) painterResource(id = R.drawable.ic_user)
            else rememberAsyncImagePainter(model = imgUri), contentDescription = "",
            modifier = Modifier
                .size(120.dp)
                .padding(16.dp)
                .clip(CircleShape)
                .clickable {
                    val isGranted = ContextCompat.checkSelfPermission(
                        context, permissionToRequest
                    ) == PackageManager.PERMISSION_GRANTED

                    if (isGranted) {
                        launcher.launch("image/*")
                    } else {
                        permissionLauncher.launch(permissionToRequest)
                    }
                },
            contentScale = ContentScale.Crop

        )
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
        OutlinedTextField(
            value = userName,
            onValueChange = {userName = it},
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "User Name")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Name")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        OutlinedTextField(
            value = bio,
            onValueChange = {bio = it},
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "bio")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        Spacer(modifier = Modifier.size(20.dp))
        ElevatedButton(
            onClick = {
                      if(name.isEmpty() || email.isEmpty() || pass.isEmpty() || bio.isEmpty() || imgUri == null) {
                          Toast.makeText(context, "fill all details", Toast.LENGTH_SHORT).show()
                      } else {
                          authViewModel.register(email, pass, name, bio, userName, imgUri!!, context)
                      }

                      },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "Register")
        }
        Spacer(modifier = Modifier.size(10.dp))
        TextButton(
            onClick = {
                navController.navigate(Screens.Login.routes){
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
                      },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "Already have account? Login")
        }


    }
}

@Preview(showSystemUi = true)
@Composable
fun RegPrev() {
    //Register(navController)
}
