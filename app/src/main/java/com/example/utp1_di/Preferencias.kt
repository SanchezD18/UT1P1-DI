package com.example.utp1_di

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RatingBar2(
    modifier:Modifier=Modifier,
    rating:Int=0,
    stars:Int=10,
    starsColor:Color=Color.Red,
    onRatingChanged: (Int) -> Unit = {}
){
    Row(modifier=modifier){
        for (i in 1..stars) {
            val icon = if (i <= rating) {
                Icons.Filled.Favorite
            } else {
                Icons.Filled.FavoriteBorder
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier
                    .clickable {
                    onRatingChanged(i)
                })
        }
    }
}

@Composable
fun PreferencesPrincipal(navController : NavController, modifier: Modifier){
    val orientation = LocalConfiguration.current.orientation
    if (orientation == ORIENTATION_LANDSCAPE) {
        PreferencesHorizontal(modifier)
    } else {
        PreferencesVertical(modifier)
    }

}

@Composable
fun PreferencesVertical(modifier: Modifier){
    var positionSlider by remember { mutableFloatStateOf(0f) }
    var rating by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val radioOptions = listOf("La puerta", "Una corte de rosas y espinas", "Por si las voces vuelven",
        "La mansión Starling", "La psicóloga", "Delito", "El fugitivo")
    val (selectedRadio, onOptionSelected) = remember { mutableStateOf("") }
    val chipOptions = listOf("Kindle", "Audible", "Nextory", "Prime Reading")


    Box(modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            modifier = modifier,
            text = "Elige una opción:",
            fontSize = 20.sp,
            fontFamily = GoudyFont
        )

        Column(modifier
            .selectableGroup()) {
            Spacer(modifier
                .height(3.dp))
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 16.dp)
                        .selectable(
                            selected = (text == selectedRadio),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedRadio),
                        onClick = null
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
            Slider(
                value = positionSlider,
                onValueChange = { newValue ->
                    positionSlider = newValue
                },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                steps = 10,
                valueRange = 0f..10f
            )
            Column(modifier.padding(60.dp,5.dp,10.dp,5.dp)){
                RatingBar2(
                    rating = rating,
                    stars = 10,
                    onRatingChanged = { newRating ->
                        rating = newRating
                    })

            }
            Text(
                modifier = modifier,
                text = "Plataformas:",
                fontSize = 20.sp,
            )
            Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val (chipSelection, onChipSelected) = remember {mutableStateOf("")}
                chipOptions.forEach { text ->
                    FilterChip(
                        selected = (text == chipSelection),
                        onClick = {
                            if (text == chipSelection) {
                                onChipSelected("")
                            } else {
                                onChipSelected(text)
                                Toast.makeText(context, "Has seleccionado $text", Toast.LENGTH_LONG).show()
                            }
                        },
                        label = {Text(text)},
                        leadingIcon = if (text == chipSelection) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        }
                    )
                } }
        }

        SmallFloatingActionButton(
            onClick = {
                val message = if (selectedRadio.isEmpty()) {
                    "No has seleccionado ninguna opción."
                } else {
                    "Has seleccionado: $selectedRadio con un rating de $rating estrellas."
                }
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    16.dp,70.dp
                ),
            containerColor = Color.White,
            contentColor = Color.Black,
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Agregar a favoritos"
            )
        }

        FloatingActionButton(
            onClick = {
                val message = if (selectedRadio.isEmpty()) {
                    "No has seleccionado ninguna opción."
                } else {
                    "Has seleccionado: $selectedRadio con una puntuación de ${positionSlider.toInt()}."
                }
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Confirmar selección"
            )
        }

    }
}



@Composable
fun PreferencesHorizontal(modifier: Modifier){
    var positionSlider by remember { mutableFloatStateOf(0f) }
    var rating by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val radioOptions = listOf("La puerta", "Una corte de rosas y espinas", "Por si las voces vuelven",
        "La mansión Starling", "La psicóloga", "Delito", "El fugitivo")
    val (selectedRadio, onOptionSelected) = remember { mutableStateOf("") }
    val chipOptions = listOf("Kindle", "Audible", "Nextory", "Prime Reading")
    Box(modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {
        Text(
            modifier = modifier,
            text = "Elige una opción:",
            fontSize = 20.sp,
            fontFamily = GoudyFont
        )

        Column(modifier.selectableGroup()) {
            Spacer(modifier.height(3.dp))
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedRadio),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedRadio),
                        onClick = null
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
            Slider(
                value = positionSlider,
                onValueChange = { newValue ->
                    positionSlider = newValue
                },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                steps = 10,
                valueRange = 0f..10f
            )
            Column(modifier.padding(60.dp,5.dp,10.dp,5.dp)){
                RatingBar2(
                    rating = rating,
                    stars = 10,
                    onRatingChanged = { newRating ->
                        rating = newRating
                    })

            }
            Text(
                modifier = modifier,
                text = "Plataformas:",
                fontSize = 20.sp,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val (chipSelection, onChipSelected) = remember {mutableStateOf("")}
                chipOptions.forEach { text ->
                    FilterChip(
                        selected = (text == chipSelection),
                        onClick = {
                            if (text == chipSelection) {
                                onChipSelected("")
                            } else {
                                onChipSelected(text)
                                Toast.makeText(context, "Has seleccionado $text", Toast.LENGTH_LONG).show()
                            }
                        },
                        label = {Text(text)},
                        leadingIcon = if (text == chipSelection) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        }
                    )
                } }
        }

        SmallFloatingActionButton(
            onClick = {
                val message = if (selectedRadio.isEmpty()) {
                    "No has seleccionado ninguna opción."
                } else {
                    "Has seleccionado: $selectedRadio con un rating de $rating estrellas."
                }
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    16.dp,80.dp
                ),
            containerColor = Color.White,
            contentColor = Color.Black,
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Agregar a favoritos"
            )
        }

        FloatingActionButton(
            onClick = {
                val message = if (selectedRadio.isEmpty()) {
                    "No has seleccionado ninguna opción."
                } else {
                    "Has seleccionado: $selectedRadio con una puntuación de ${positionSlider.toInt()}."
                }
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Confirmar selección"
            )
        }

    }
}

