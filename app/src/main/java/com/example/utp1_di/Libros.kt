package com.example.utp1_di

import android.R.id.icon
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



data class CheckInfo(var title:String, var selected:Boolean, var
onCheckedChange:(Boolean)->Unit)


@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var estadoCheck by remember { mutableStateOf(false)
        }
        CheckInfo(
            title = it,
            selected = estadoCheck,
            onCheckedChange = { estadoCheck = it }
        )
    }}

@Composable
fun MyCheckBox(checkInfo:CheckInfo){
    Row(Modifier.padding(8.dp)){
        Checkbox(
            checked=checkInfo.selected,
            onCheckedChange = {
                checkInfo.onCheckedChange(!checkInfo.selected)})
        Spacer(modifier=Modifier.width(8.dp))
        Text(text=checkInfo.title)
    }
}

@Composable
fun LibrosPrincipal(navController : NavController, modifier: Modifier){
    val orientation = LocalConfiguration.current.orientation
    if (orientation == ORIENTATION_LANDSCAPE) {
        LibrosHorizontal(navController, modifier)
    } else {
        LibrosVertical(modifier)
    }

}

@Composable
fun LibrosVertical(modifier: Modifier){
    val context = LocalContext.current

    Box(modifier.fillMaxSize().padding(16.dp)) {

        Column (modifier = modifier) {
            val myOptions = getOptions(listOf("La puerta", "Una corte de rosas y espinas", "El fugitivo", "Delito"))

            Text(
                modifier = modifier,
                text = "Lista de Libros:",
                fontSize = 20.sp,
                fontFamily = GoudyFont
            )
            Spacer(Modifier.size(30.dp))
            Column() {
                myOptions.forEach {
                    Image(
                        painter = painterResource(id = R.drawable.android),
                        contentDescription = "Imagenes",
                        modifier = Modifier.requiredSize(80.dp)
                    )
                    MyCheckBox(it)
                }
            }
        }
        FloatingActionButton(
            onClick = {
                Toast.makeText(context, "A", Toast.LENGTH_LONG).show()
            },
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Confirmar selección"
            )
        }

    }
}



@Composable
fun LibrosHorizontal(navController : NavController, modifier: Modifier){
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
        FilledButtonExample("Libros") { }
        FilledButtonExample("Usuarios") { }
        FilledButtonExample("Búsqueda") { }
        FilledButtonExample("Nuevo Usuario") { navController.navigate("NuevoUsuario") }
    }}
