package com.example.utp1_di

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


data class LibrosItem(
    var nombre: String,
    var mejorObra: String,
    var nacimiento: String,
    @DrawableRes var photo: Int
)

fun getLibrosItem() : List<LibrosItem>{
    return listOf(
        LibrosItem(
            "Miguel de Cervantes",
            "El quijote",
            "29/09/1547",
            R.drawable.cervantes
        ),
        LibrosItem(
            "Charles Dickens",
            "Oliver Twist",
            "07/02/1812",
            R.drawable.dickens
        ),
        LibrosItem(
            "John Ronald Reuel Tolkien",
            "El señor de los anillos",
            "03/01/1892",
            R.drawable.tolkien
        ),
        LibrosItem(
            "Joanne Rowling",
            "Harry Potter",
            "31/06/1965",
            R.drawable.rowling
        ),
        LibrosItem(
            "Agatha Christie",
            "Asesinato en el Orient Express",
            "15/09/1890",
            R.drawable.agatha
        ),
        LibrosItem(
            "William Shakespeare",
            "Romeo y Julieta",
            "??/04/1964",
            R.drawable.shakespeare
        ),
        LibrosItem(
            "Eiichiro Oda",
            "One Piece",
            "01/01/1975",
            R.drawable.oda
        ),
        LibrosItem(
            "Dan Brown",
            "El código Da Vinci",
            "22/06/1964",
            R.drawable.brownjpeg
        ))}

@Composable
fun ItemAutor(autor:LibrosItem){
    val context = LocalContext.current
    Card(modifier =
        Modifier.fillMaxWidth()
            .size(150.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8D0FF)),
        onClick = {
                Toast.makeText(context, autor.nombre, Toast.LENGTH_LONG).show()
        }) {
            Row{
                    Image(
                        painter = painterResource(id = autor.photo),
                        contentDescription = "Cara Autores",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Inside,
                    )

                Column(modifier = Modifier
                    .weight(1f)){
                    Spacer(modifier = Modifier.size(40.dp))
                    Text(
                        text = autor.nombre,
                        modifier =
                            Modifier.align(Alignment.CenterHorizontally),
                    )
                    Text(
                        text = autor.mejorObra,
                        modifier =
                            Modifier.align(Alignment.CenterHorizontally),
                        fontSize = 14.sp
                    )
                    Text(
                        text = autor.nacimiento,
                        modifier =
                            Modifier.align(Alignment.CenterHorizontally),
                        fontSize = 12.sp

                    )
                }
            }
    }
}

@Composable
fun AutoresView(){
    LazyColumn(
){
        items(getLibrosItem()){ superHero->
            ItemAutor(autor=superHero)
            HorizontalDivider()
        }
    }
}

@Composable
fun AboutPrincipal(navController : NavController, modifier: Modifier){
    val orientation = LocalConfiguration.current.orientation
    if (orientation == ORIENTATION_LANDSCAPE) {
        AboutHorizontal(modifier)
    } else {
        AboutVertical(modifier)
    } }


@Composable
fun AboutVertical(modifier: Modifier){
    AutoresView()
}



@Composable
fun AboutHorizontal(modifier: Modifier){
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
