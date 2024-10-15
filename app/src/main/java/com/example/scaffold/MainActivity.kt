package com.example.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.scaffold.ui.theme.ScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTheme {
                ScaffoldApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { MyTopBar(navController) },
        bottomBar = { BottomAppBarContent()},
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "Koti",
                Modifier.padding(paddingValues)
            ) {
                composable(route = "Koti") { MainScreen(navController) }
                composable(route = "Info") { InfoScreen(navController) }
                composable(route = "Asetukset") { SettingsScreen(navController) }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavController) {
    val expanded = remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Scaffold") },
        actions = {
            IconButton(onClick = { expanded.value = true }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More options")
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = {
                        expanded.value = false
                        navController.navigate("Info")
                    }
                )
                DropdownMenuItem(
                    text = { Text("Asetukset") },
                    onClick = {
                        expanded.value = false
                        navController.navigate("Asetukset")
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController, backgroundColor: Color) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
    )
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Koti", navController, Color.Blue) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Koti-sivu")
            }
        }
    )
}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Info", navController, Color.Blue) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Info-sivu")
            }
        }
    )
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Settings", navController, Color.Blue) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Asetukset-sivu")
            }
        }
    )
}
@Composable
fun BottomAppBarContent() {
    BottomAppBar(
        containerColor = Color.Blue
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Alapalkki", color = Color.White)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldTheme {
        ScaffoldApp()
    }
}
