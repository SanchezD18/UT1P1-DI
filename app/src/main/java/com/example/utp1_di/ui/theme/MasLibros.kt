package com.example.utp1_di.ui.theme

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.health.connect.datatypes.units.Mass
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.utp1_di.GoudyFont
import com.example.utp1_di.MyCheckBox
import com.example.utp1_di.R
import com.example.utp1_di.getOptions



data class SuperHero(
    var superheroName: String,
    var realName: String,
    var publisher: String,
    @DrawableRes var photo: Int
)

fun getSuperHeroes() : List<SuperHero>{
    return listOf(
        SuperHero(
            "Spider-man",
            "PeterParker",
            "Marvel",
            R.drawable.image1
        ),
        SuperHero(
            "DareDevil",
            "MattMurdock",
            "Marvel",
            R.drawable.image2
        ),
        SuperHero(
            "GhostSpider",
            "GwenStacy",
            "Marvel",
            R.drawable.image3
        ),
        SuperHero(
            "GhostSpider",
            "GwenStacy",
            "Marvel",
            R.drawable.image4
        ),
        SuperHero(
            "GhostSpider",
            "GwenStacy",
            "Marvel",
            R.drawable.image5
        ),
        SuperHero(
            "GhostSpider",
            "GwenStacy",
            "Marvel",
            R.drawable.image6
        ),
        SuperHero(
            "GhostSpider",
            "GwenStacy",
            "Marvel",
            R.drawable.image7
        ),
        SuperHero(
            "GhostSpider",
            "GwenStacy",
            "Marvel",
            R.drawable.image8
        ))}

@Composable
fun ItemHero(superhero:SuperHero){
    Card(border = BorderStroke(2.dp, Color.Red), modifier =
        Modifier.fillMaxWidth()){
            Row {
                Column {
                    Image(
                        painter = painterResource(id = superhero.photo),
                        contentDescription = "SuperHeroavatar",
                        modifier = Modifier
                            .height(100.dp),
                        contentScale = ContentScale.Inside
                    )
                }

                Column {
                    Text(
                        text = superhero.superheroName,
                        modifier =
                            Modifier.align(Alignment.End),
                    )
                    Text(
                        text = superhero.realName,
                        modifier =
                            Modifier.align(Alignment.End),
                        fontSize = 12.sp
                    )
                    Text(
                        text = superhero.publisher,
                        modifier =
                            Modifier.align(Alignment.End),
                        fontSize = 10.sp

                    )
                }
            }
    }
}

@Composable
fun SuperHeroView(){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)){
        items(getSuperHeroes()){superHero->
            ItemHero(superhero=superHero)
        }
    }
}

@Composable
fun MasLibrosPrincipal(navController : NavController, modifier: Modifier){
    val orientation = LocalConfiguration.current.orientation
    if (orientation == ORIENTATION_LANDSCAPE) {
        MasLibrosHorizontal(modifier)
    } else {
        MasLibrosVertical(modifier)
    } }


@Composable
fun MasLibrosVertical(modifier: Modifier){
    val context = LocalContext.current
    SuperHeroView()
}



@Composable
fun MasLibrosHorizontal(modifier: Modifier){
    val context = LocalContext.current

    Box(modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {
        val bookTitles = listOf("La puerta", "Una corte de rosas y espinas", "El fugitivo", "Delito")
        val myOptions = getOptions(bookTitles)
        val selectedBooks = myOptions.filter { it.selected }.map { it.title }

        Column (modifier = modifier) {
            val bookImages = listOf(
                R.drawable.lapuerta,
                R.drawable.unacortederosasyespinas,
                R.drawable.elfugitivo,
                R.drawable.delito
            )
            Text(
                modifier = modifier,
                text = "Lista de Libros:",
                fontSize = 20.sp,
                fontFamily = GoudyFont
            )
            Spacer(Modifier.size(30.dp))
            myOptions.forEachIndexed { index, checkInfo ->
                Row(modifier.padding(10.dp)) {Image(
                    painter = painterResource(id = bookImages[index]),
                    contentDescription = "Imagenes",
                    modifier = Modifier.requiredSize(80.dp)
                )
                    MyCheckBox(checkInfo)
                }
            }

        }

        FloatingActionButton(
            onClick = {
                val mensaje = if (selectedBooks.isEmpty()) {
                    "No has seleccionado ningún libro"
                } else {
                    "Has seleccionado: ${selectedBooks.joinToString(", ")}"
                }
                Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()
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

    }}
