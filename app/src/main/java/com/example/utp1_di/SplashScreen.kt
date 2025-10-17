package com.example.utp1_di

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, modifier: Modifier){
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.montanalibros),
            contentDescription = "Imagen centrada",
            modifier = Modifier.size(150.dp)
        )
        Text(
            modifier = modifier
                .padding(20.dp),
            text = "DaviTeca \n creado por David Sánchez",
            fontSize = 15.sp,
            fontFamily = GoudyFont,
            textAlign = TextAlign.Center
        )
    }
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate("Menu"){
            popUpTo("SplashScreen") { inclusive = true }
            launchSingleTop = true
        }
    }

}