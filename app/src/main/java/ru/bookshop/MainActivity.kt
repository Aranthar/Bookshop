package ru.bookshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.bookshop.ui.navigation.BottomNavigationBar
import ru.bookshop.ui.navigation.NavGraph
import ru.bookshop.ui.navigation.Screens
import ru.bookshop.ui.theme.BookshopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            BookshopTheme {
                Scaffold(
                    bottomBar = {
                        val currentRoute = getCurrentRoute(navBackStackEntry)
                        if (currentRoute != Screens.DetailsScreen.name) {
                            BottomNavigationBar(
                                navController = navController,
                                navBackStackEntry = navBackStackEntry,
                                items = Screens.getBottomItems(),
                            )
                        }
                    }
                ) { innerPaddings ->
                    NavGraph(
                        navController = navController,
                        innerPaddings = innerPaddings
                    ).Create()
                }
            }
        }
    }
}

@Composable
fun getCurrentRoute(navBackStackEntry: NavBackStackEntry?): String? {
    return navBackStackEntry?.destination?.route
}