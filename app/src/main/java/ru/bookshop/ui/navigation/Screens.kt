package ru.bookshop.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screens(val title: String? = null, val imageVector: ImageVector? = null) {
    SplashScreen,
    HomeScreen("Главная", Icons.Filled.Home),
    AccountScreen("Профиль", Icons.Filled.AccountCircle),
    AuthorsScreen("Об авторах", Icons.Filled.Info),
    DetailsScreen,
    AccountEditScreen;

    companion object {
        fun getBottomItems(): List<Screens> {
            return listOf(HomeScreen, AccountScreen, AuthorsScreen)
        }
    }
}