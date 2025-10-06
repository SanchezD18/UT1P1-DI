package com.example.utp1_di

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun OutlinedButtonExample(texto: String, onClick: () -> Unit) {
    OutlinedButton(onClick = { onClick() }) {
        Text(texto)
    }
}

@Composable
fun NuevoUsuarioPrincipal(navController : NavController, modifier: Modifier){
    val orientation = LocalConfiguration.current.orientation
    if (orientation == ORIENTATION_LANDSCAPE) {
        NuevoUsuarioHorizontal(navController, modifier)
    } else {
        NuevoUsuarioVertical(navController, modifier)
    }

}

@Composable
fun NuevoUsuarioVertical(navController: NavController, modifier: Modifier) {
    var estadoNombre by remember { mutableStateOf("") }
    var estadoApellido by remember { mutableStateOf("") }
    var estadoNickname by remember { mutableStateOf("") }
    var estadoTelefono by remember { mutableStateOf("") }
    var estadoEmail by remember { mutableStateOf("") }
    var mensajeErrorNombre by remember { mutableStateOf("") }
    var mensajeErrorNickname by remember { mutableStateOf("") }

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            modifier = modifier,
            text = "Formulario",
            fontSize = 30.sp,
        )
        Column {
            Row {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.account),
                        contentDescription = "Persona",
                        modifier = Modifier.requiredSize(80.dp)
                    )
                }
                Column {

                    OutlinedTextField(
                        value = estadoNombre,
                        onValueChange = { estadoNombre = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.padding(10.dp)
                    )
                    if (!mensajeErrorNombre.isEmpty()) {
                        Text(
                            text = mensajeErrorNombre,
                            color = Color.Red,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                    OutlinedTextField(
                        value = estadoApellido,
                        onValueChange = { estadoApellido = it },
                        label = { Text("Apellido") },
                        modifier = Modifier.padding(10.dp)
                    )

                    OutlinedTextField(
                        value = estadoNickname,
                        onValueChange = { estadoNickname = it },
                        label = { Text("Nickname") },
                        modifier = Modifier.padding(10.dp)
                    )
                    if (!mensajeErrorNickname.isEmpty()) {
                        Text(
                            text = mensajeErrorNickname,
                            color = Color.Red,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.android),
                            contentDescription = "Android",
                            modifier = Modifier.requiredSize(80.dp)
                        )
                        Spacer(modifier.height(10.dp).width(10.dp))
                        Button(onClick = {}) { Text("Cambiar") }
                    }
                }
            }
            Row {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "Camara",
                        modifier = Modifier.requiredSize(80.dp)
                    )
                }
                Column {

                    OutlinedTextField(
                        value = estadoTelefono,
                        onValueChange = { estadoTelefono = it },
                        label = { Text("Teléfono") },
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            Row {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = "Carta",
                        modifier = Modifier.requiredSize(80.dp)
                    )
                }
                Column {

                    OutlinedTextField(
                        value = estadoEmail,
                        onValueChange = { estadoEmail = it },
                        label = { Text("Email") },
                        modifier = Modifier.padding(10.dp),
                    )

                    OutlinedButtonExample("Agregar jugador nuevo.") {
                        if (estadoNombre.isBlank()) {
                            mensajeErrorNombre = "Este campo es obligatorio."
                        } else {
                            mensajeErrorNombre = ""
                        }
                    }
                    if (estadoNickname.isBlank()) {
                        mensajeErrorNickname = "Este campo es obligatorio."
                    } else {
                        mensajeErrorNickname = ""
                    }
                    if (!estadoNombre.isBlank() && !estadoNickname.isBlank()){
                        val textoValido = "Formulario válido. Nombre: $estadoNombre, NickName: $estadoNickname"
                        Text(
                            text = textoValido,
                            color = Color.Green,
                            modifier = Modifier.padding(4.dp)
                        )}
                }

            }
        }
    }
}
@Composable
fun NuevoUsuarioHorizontal(navController: NavController, modifier: Modifier){
    Column(modifier.fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Text(
            modifier = modifier,
            text = "Formulario",
            fontSize = 30.sp,
        )
        Row {
            Column {Row { Image(
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
                    Column { Image(
                        painter = painterResource(id = R.drawable.android),
                        contentDescription = "Android",
                        modifier = Modifier.requiredSize(80.dp))
                        Spacer(modifier.height(10.dp).width(10.dp))
                        Button(onClick = {}) { Text("Change")} }}
            }
            Column {
                Row { Image(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "Camara",
                    modifier = Modifier.requiredSize(80.dp)
                ) }
                Row {
                    var estadoTelefono by remember{mutableStateOf(" ")}
                    OutlinedTextField(
                        value = estadoTelefono,
                        onValueChange = { estadoTelefono = it },
                        label = { Text(text = "Teléfono") },
                        modifier = Modifier.padding(10.dp)) }
            }
            Column {
                Row { Image(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "Carta",
                    modifier = Modifier.requiredSize(80.dp)
                ) }
                Row {
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
