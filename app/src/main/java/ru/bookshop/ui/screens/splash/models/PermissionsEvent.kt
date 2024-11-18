package ru.bookshop.ui.screens.splash.models

sealed class PermissionsEvent {
    data object NotGranted : PermissionsEvent()
    data object DismissPermission : PermissionsEvent()
}
