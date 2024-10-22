package ru.bookshop.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import ru.bookshop.getCurrentRoute

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<Screens>,
) {
    NavigationBar {
        val currentRoute = getCurrentRoute(navController)

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.name,
                icon = {
                    if (item.imageVector != null) {
                        Icon(imageVector = item.imageVector, contentDescription = item.title)
                    }
                },
                label = {
                    if (item.title != null) {
                        Text(item.title)
                    }
                },
                alwaysShowLabel = false,
                onClick = {
                    when (item.name) {
                        currentRoute -> return@NavigationBarItem
                        Screens.CatalogScreen.name -> {
                            navController.navigate(Screens.CatalogScreen.name) {
                                popUpTo(Screens.HomeScreen.name) { inclusive = false }
                            }
                        }
                        Screens.AuthorsScreen.name -> {
                            navController.navigate(Screens.AuthorsScreen.name) {
                                popUpTo(Screens.HomeScreen.name) { inclusive = false }
                            }
                        }
                        else -> {
                            navController.navigate(Screens.HomeScreen.name) {
                                popUpTo(Screens.HomeScreen.name) { inclusive = false }
                            }
                        }
                    }
                }
            )
        }
    }
}