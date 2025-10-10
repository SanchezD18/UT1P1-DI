package com.example.utp1_di

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
        NuevoUsuarioHorizontal(modifier)
    } else {
        NuevoUsuarioVertical(modifier)
    }

}


@Composable
fun NuevoUsuarioVertical(modifier: Modifier) {
    var estadoNombre by remember { mutableStateOf("") }
    var estadoApellido by remember { mutableStateOf("") }
    var estadoNickname by remember { mutableStateOf("") }
    var estadoTelefono by remember { mutableStateOf("") }
    var mensajeErrorNombre by remember { mutableStateOf("") }
    var mensajeErrorNickname by remember { mutableStateOf("") }
    var mensajeFormularioValido by remember { mutableStateOf("") }
    var validacionRealizada by remember { mutableStateOf(false) }

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
                        onValueChange = { 
                            estadoNombre = it
                            if (it.isNotBlank() && validacionRealizada) {
                                mensajeErrorNombre = ""
                            }
                        },
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
                        onValueChange = { 
                            estadoNickname = it
                            if (it.isNotBlank() && validacionRealizada) {
                                mensajeErrorNickname = ""
                            }
                        },
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
                        Button(onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                        ){ Text("Cambiar") }
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

                    var selectedText by remember { mutableStateOf("") }
                    var expanded by remember { mutableStateOf(false) }
                    val series = listOf("davidyo99@gmail.com", "smdavid1999@gmail.com", "davsanman2@alu.edu.gva.es", "al375850@uji.es")

                    OutlinedTextField(
                        value = selectedText,
                        onValueChange = { selectedText = it },
                        label = { Text("Email") },
                        enabled = false,
                        readOnly = true,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { expanded = true }
                            .fillMaxWidth()
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        series.forEach { serie ->
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    selectedText = serie
                                },
                                text = { Text(text = serie) }
                            )
                        }
                    }

                    OutlinedButtonExample("Agregar jugador nuevo.") {
                        validacionRealizada = true
                        mensajeFormularioValido = ""

                        mensajeErrorNombre = if (estadoNombre.isBlank()) {
                            "Este campo es obligatorio."
                        } else {
                            ""
                        }

                        mensajeErrorNickname = if (estadoNickname.isBlank()) {
                            "Este campo es obligatorio."
                        } else {
                            ""
                        }

                        if (estadoNombre.isNotBlank() && estadoNickname.isNotBlank()) {
                            mensajeFormularioValido = "Formulario válido."
                        }
                    }
                    if (mensajeFormularioValido.isNotEmpty()) {
                        Text(
                            text = mensajeFormularioValido,
                            color = Color.Green,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }

            }
        }
    }
}
@Composable
fun NuevoUsuarioHorizontal(modifier: Modifier){
    var estadoNombre by remember { mutableStateOf("") }
    var estadoApellido by remember { mutableStateOf("") }
    var estadoNickname by remember { mutableStateOf("") }
    var estadoTelefono by remember { mutableStateOf("") }
    var estadoEmail by remember { mutableStateOf("") }
    var mensajeErrorNombre by remember { mutableStateOf("") }
    var mensajeErrorNickname by remember { mutableStateOf("") }
    var mensajeFormularioValido by remember { mutableStateOf("") }
    var validacionRealizada by remember { mutableStateOf(false) }
    
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
            Column {
                Row { 
                    Image(
                        painter = painterResource(id = R.drawable.account),
                        contentDescription = "Persona",
                        modifier = Modifier.requiredSize(80.dp)
                    ) 
                }
                Column { 
                    OutlinedTextField(
                        value = estadoNombre,
                        onValueChange = { 
                            estadoNombre = it
                            if (it.isNotBlank() && validacionRealizada) {
                                mensajeErrorNombre = ""
                            }
                        },
                        label = { Text(text = "Nombre") },
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
                        label = { Text(text = "Apellido") },
                        modifier = Modifier.padding(10.dp)
                    )
                    
                    OutlinedTextField(
                        value = estadoNickname,
                        onValueChange = { 
                            estadoNickname = it
                            if (it.isNotBlank() && validacionRealizada) {
                                mensajeErrorNickname = ""
                            }
                        },
                        label = { Text(text = "Nickname") },
                        modifier = Modifier.padding(10.dp)
                    )
                    if (!mensajeErrorNickname.isEmpty()) {
                        Text(
                            text = mensajeErrorNickname,
                            color = Color.Red,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    
                    Column { 
                        Image(
                            painter = painterResource(id = R.drawable.android),
                            contentDescription = "Android",
                            modifier = Modifier.requiredSize(80.dp)
                        )
                        Spacer(modifier.height(10.dp).width(10.dp))
                        Button(onClick = {}) { Text("Change")} 
                    }
                }
            }
            Column {
                Row { 
                    Image(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "Camara",
                        modifier = Modifier.requiredSize(80.dp)
                    ) 
                }
                Row {
                    OutlinedTextField(
                        value = estadoTelefono,
                        onValueChange = { estadoTelefono = it },
                        label = { Text(text = "Teléfono") },
                        modifier = Modifier.padding(10.dp)
                    ) 
                }
            }
            Column {
                Row { 
                    Image(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = "Carta",
                        modifier = Modifier.requiredSize(80.dp)
                    ) 
                }
                Row {
                    OutlinedTextField(
                        value = estadoEmail,
                        onValueChange = { estadoEmail = it },
                        label = { Text(text = "Email")},
                        modifier = Modifier.padding(10.dp),
                        placeholder = {Text(text = "Alcachofa")}
                    ) 
                }
            }
        }

        OutlinedButtonExample("Agregar jugador nuevo.") {
            validacionRealizada = true
            mensajeFormularioValido = ""

            mensajeErrorNombre = if (estadoNombre.isBlank()) {
                "Este campo es obligatorio."
            } else {
                ""
            }

            mensajeErrorNickname = if (estadoNickname.isBlank()) {
                "Este campo es obligatorio."
            } else {
                ""
            }

            if (estadoNombre.isNotBlank() && estadoNickname.isNotBlank()) {
                mensajeFormularioValido = "Formulario válido. Nombre: $estadoNombre, NickName: $estadoNickname"
            }
        }

        if (mensajeFormularioValido.isNotEmpty()) {
            Text(
                text = mensajeFormularioValido,
                color = Color.Green,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
