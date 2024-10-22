package ru.bookshop.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import ru.bookshop.R
import ru.bookshop.data.models.BookDTO
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
            composable(
                route = "${Screens.DetailsScreen.name}?$BOOK_ID={$BOOK_ID}",
                arguments = listOf(navArgument(BOOK_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                })
            ) {
                CreateDetailsScreen()
            }
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
                navController.navigate("${Screens.DetailsScreen.name}?$BOOK_ID=$it")
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
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val arguments = navBackStackEntry?.arguments ?: return
        val bookId = arguments.getInt(BOOK_ID, -1)

        DetailsScreen(
            id = bookId,
            onBackClick = { navController.popBackStack() },
        )
    }

    companion object {
        const val BOOK_ID = "BOOK_ID"
    }
}