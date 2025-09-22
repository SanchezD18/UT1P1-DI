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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource

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
            fontSize = 30.sp,
        )
        Spacer(modifier.height(20.dp))
        FilledButtonExample("Libros") { }
        FilledButtonExample("Usuarios") { }
        FilledButtonExample("Búsqueda") { }
        FilledButtonExample("Nuevo Usuario") { navController.navigate("NuevoUsuario") }
}}

@Composable
fun MenuPrincipalHorizontal(navController : NavController, modifier: Modifier){
    Column (modifier.fillMaxSize(),
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
        Row(modifier = modifier) { FilledButtonExample("Búsqueda") { }
            Spacer(modifier = modifier
                .width(10.dp))
            FilledButtonExample("Usuarios") { } }
        Row(modifier = modifier) {
            FilledButtonExample("Nuevo Usuario") { navController.navigate("NuevoUsuario") }
        }


    }}

@Composable
fun NuevoUsuario(navController: NavController, modifier: Modifier){
    Column (modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Text(
            modifier = modifier,
            text = "Formulario",
            fontSize = 30.sp,
        )
    Column {
        Row {Column { Image(
            painter = painterResource(id = R.drawable.account),
            contentDescription = "Persona",
            modifier = Modifier.requiredSize(80.dp)) }
            Column { var estadoNombre by remember{mutableStateOf(" ") }
                OutlinedTextField(
                    value = estadoNombre,
                    onValueChange = { estadoNombre = it },
                    label = { Text(text = "Nombre") },
                    modifier = Modifier.padding(10.dp))
                var estadoApellido by remember{mutableStateOf(" ") }
                OutlinedTextField(
                    value = estadoApellido,
                    onValueChange = { estadoApellido = it },
                    label = { Text(text = "Nombre") },
                    modifier = Modifier.padding(10.dp))
                Row { Image(
                    painter = painterResource(id = R.drawable.android),
                    contentDescription = "Android",
                    modifier = Modifier.requiredSize(80.dp))
                    Spacer(modifier.height(10.dp).width(10.dp))
                    Button(onClick = {}) { Text("Change")} }}
        }
        Row {
            Column { Image(
                painter = painterResource(id = R.drawable.camera),
                contentDescription = "Camara",
                modifier = Modifier.requiredSize(80.dp)
            ) }
            Column {
                var estadoTelefono by remember{mutableStateOf(" ")}
                OutlinedTextField(
                value = estadoTelefono,
                onValueChange = { estadoTelefono = it },
                label = { Text(text = "Teléfono") },
                modifier = Modifier.padding(10.dp)) }
              }
        Row {
            Column { Image(
                painter = painterResource(id = R.drawable.email),
                contentDescription = "Carta",
                modifier = Modifier.requiredSize(80.dp)
            ) }
            Column {
                var estadoEmail by remember{mutableStateOf(" ")}
                OutlinedTextField(
                    value = estadoEmail,
                    onValueChange = { estadoEmail = it },
                    label = { Text(text = "Email")},
                    modifier = Modifier.padding(10.dp),
                    placeholder = {Text(text = "Alcachofa")}) }
        }
    }
    }

    }


