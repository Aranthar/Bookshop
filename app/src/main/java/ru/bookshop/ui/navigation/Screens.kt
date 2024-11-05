package ru.bookshop.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screens(val title: String? = null, val imageVector: ImageVector? = null) {
    HomeScreen("Главная", Icons.Filled.Home),
    CatalogScreen("Избранное", Icons.AutoMirrored.Filled.List),
    AuthorsScreen("Об авторах", Icons.Filled.Info),
    DetailsScreen;

    companion object {
        fun getBottomItems(): List<Screens> {
            return listOf(HomeScreen, CatalogScreen, AuthorsScreen)
        }
    }
}