package com.example.utp1_di

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.utp1_di.ui.theme.MasLibrosPrincipal
import com.example.utp1_di.ui.theme.SplashScreen
import com.example.utp1_di.ui.theme.UTP1DITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UTP1DITheme {
                val navController = rememberNavController()
                val startDestination = "SplashScreen"

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController, startDestination, Modifier.padding(innerPadding)) {
                        composable("SplashScreen") {
                            SplashScreen(
                                navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable("Menu") {
                            MenuPrincipal(
                                navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable("NuevoUsuario") {
                            NuevoUsuarioPrincipal(
                                navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable("Preferences") {
                            PreferencesPrincipal(
                                navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable("Libros") {
                            LibrosPrincipal(
                                navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable("MasLibros") {
                            MasLibrosPrincipal(
                                navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}
