package ru.bookshop.ui.screens.splash.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ru.bookshop.ui.screens.splash.presentation.PermissionsViewModel
import ru.bookshop.ui.screens.splash.ui.views.PermissionsScreen

@Composable
fun SplashScreen(exit: () -> Unit, onNextScreen: () -> Unit) {
    val permissionsViewModel = hiltViewModel<PermissionsViewModel>()

    PermissionsScreen(
        viewModel = permissionsViewModel,
        onBackPressed = exit,
        onPermissionsGranted = onNextScreen
    )
}