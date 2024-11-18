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
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.screens.account.ui.AccountScreen
import ru.bookshop.ui.screens.account_edit.ui.AccountEditScreen
import ru.bookshop.ui.screens.authors.ui.AuthorsScreen
import ru.bookshop.ui.screens.books.ui.BooksScreen
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

            composable(
                route = "${Screens.AccountScreen.name}?param1={param1}&param2={param2}&param3={param3}",
            ) { CreateAccountScreen() }

            composable(
                route = "${Screens.AccountEditScreen.name}?param1={param1}&param2={param2}&param3={param3}",
                arguments = listOf(
                    navArgument("param1") { type = NavType.StringType },
                    navArgument("param2") { type = NavType.StringType },
                    navArgument("param3") { type = NavType.StringType },
                )
            ) { CreateAccountEditScreen() }

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
        BooksScreen(
            onDetailsScreen = {
                navController.navigate("${Screens.DetailsScreen.name}?$BOOK_ID=$it")
            }
        )
    }

    @Composable
    private fun CreateAccountScreen() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val arguments = navBackStackEntry?.arguments ?: return
        val param1 = arguments.getString("param1")
        val param2 = arguments.getString("param2")
        val param3 = arguments.getString("param3")

        val accountInfo = AccountDTO(
            image = R.drawable.no_photo,
            name = param1 ?: "Имя",
            job = param2 ?: "Должность",
            resumeUrl = param3 ?: "",
        )

        AccountScreen(
            newInfo = accountInfo,
            onEditClick = {
                navController.navigate("${Screens.AccountEditScreen.name}?param1=${it[0]}&param2=${it[1]}&param3=${it[2]}")
            },
        )
    }

    @Composable
    private fun CreateAccountEditScreen() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val arguments = navBackStackEntry?.arguments ?: return
        val param1 = arguments.getString("param1")
        val param2 = arguments.getString("param2")
        val param3 = arguments.getString("param3")

        val accountInfo = AccountDTO(
            image = R.drawable.no_photo,
            name = param1 ?: "Имя",
            job = param2 ?: "Должность",
            resumeUrl = param3 ?: "",
        )

        AccountEditScreen(
            info = accountInfo,
            onDoneClick = {
                navController.navigate("${Screens.AccountScreen.name}?param1=${it[0]}&param2=${it[1]}&param3=${it[2]}")
            },
            onBackClick = {
                navController.popBackStack()
            },
        )
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