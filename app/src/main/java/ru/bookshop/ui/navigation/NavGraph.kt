package ru.bookshop.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.bookshop.R
import ru.bookshop.data.models.BookDTO
import ru.bookshop.data.models.CharacteristicsDTO
import ru.bookshop.ui.screens.authors.ui.AuthorsScreen
import ru.bookshop.ui.screens.books.ui.BooksScreen
import ru.bookshop.ui.screens.catalog.ui.CatalogScreen
import ru.bookshop.ui.screens.details.ui.DetailsScreen

class NavGraph(
    private val navController: NavHostController,
    private val innerPaddings: PaddingValues,
) {
    @Composable
    fun Create() {
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier.padding(bottom = innerPaddings.calculateBottomPadding()),
            contentAlignment = Alignment.TopStart
        ) {
            composable(Screens.HomeScreen.name) { CreateHomeScreen() }
            composable(Screens.CatalogScreen.name) { CreateCatalogScreen() }
            composable(Screens.AuthorsScreen.name) { CreateAuthorsScreen() }
            composable(Screens.DetailsScreen.name) { CreateDetailsScreen() }
        }
    }

    @Composable
    private fun CreateHomeScreen() {
        val book = BookDTO(
            title = "Экстремальное программирование: разработка через тестирование",
            imageId = R.drawable.book,
            author = "Бек Кент",
            price = 1500,
            grade = 3.5f,
        )
        BooksScreen(
            books = List(8) { book },
            onDetailsScreen = {
                navController.navigate(Screens.DetailsScreen.name)
            }
        )
    }

    @Composable
    private fun CreateCatalogScreen() {
        CatalogScreen()
    }

    @Composable
    private fun CreateAuthorsScreen() {
        AuthorsScreen()
    }

    @Composable
    private fun CreateDetailsScreen() {
        DetailsScreen(
            info = CharacteristicsDTO(
                title = "Экстремальное программирование: разработка через тестирование",
                imageId = R.drawable.book,
                author = "Бек Кент",
                price = 1500,
                grade = 3.5f,
                publishingHouse = "Питер",
                series = "Библиотека программиста",
                publicationYear = 2024,
                pagesNumber = 224,
            ),
            onBackClick = { navController.popBackStack() },
        )
    }
}