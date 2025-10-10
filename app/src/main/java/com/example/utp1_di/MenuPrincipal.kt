package com.example.utp1_di

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalConfiguration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

val GoudyFont = FontFamily(Font(R.font.goudybookletter))

@Composable
fun FilledButtonExample(texto: String, onClick: () -> Unit) {
    Button(onClick = { onClick()},
        colors = ButtonDefaults.buttonColors(Color(0xFFBE2CD7))
        ,modifier = Modifier.width(200.dp).height(40.dp)
        ) {
        Text(texto)
    }}


@Composable
fun MenuPrincipal(navController : NavController, modifier: Modifier){
    val orientation = LocalConfiguration.current.orientation
    if (orientation == ORIENTATION_LANDSCAPE) {
        MenuPrincipalHorizontal(navController, modifier)
    } else {
        MenuPrincipalVertical(navController, modifier)
    }

}

@Composable
fun MenuPrincipalVertical(navController : NavController, modifier: Modifier){
    Column (modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Spacer(modifier.height(120.dp))
        Text(
            modifier = modifier,
            text = "DaviTeca",
            fontSize = 40.sp,
            fontFamily = GoudyFont
        )
        Spacer(modifier.height(20.dp))
        FilledButtonExample("Libros") { navController.navigate("Libros") }
        FilledButtonExample("Usuarios") { }
        FilledButtonExample("Preferences") { navController.navigate("Preferences") }
        FilledButtonExample("Nuevo Usuario") { navController.navigate("NuevoUsuario") }
}}


@Composable
fun MenuPrincipalHorizontal(navController : NavController, modifier: Modifier){
    Column (modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(-20.dp)) {
        Spacer(modifier = modifier
            .height(20.dp))
        Text(
            modifier = modifier,
            text = "DaviTeca",
            fontSize = 30.sp,
        )

        Row(modifier = modifier) { FilledButtonExample("Libros") { }
            Spacer(modifier = modifier
                .width(10.dp))
            FilledButtonExample("Reseñas") { } }
        Row(modifier = modifier) { FilledButtonExample("Preferences") {navController.navigate("Preferences")}
            Spacer(modifier = modifier
                .width(10.dp))
            FilledButtonExample("Nuevo Usuario") { navController.navigate("NuevoUsuario") }

        }
    }}






