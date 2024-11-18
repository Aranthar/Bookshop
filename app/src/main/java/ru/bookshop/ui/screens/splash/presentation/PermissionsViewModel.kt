package ru.bookshop.ui.screens.splash.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.splash.models.PermissionsEvent
import ru.bookshop.ui.screens.splash.models.PermissionsViewState
import javax.inject.Inject

@HiltViewModel
class PermissionsViewModel @Inject constructor() :
    BaseViewModel<PermissionsViewState, PermissionsEvent>(PermissionsViewState.CheckGranted) {

    override fun obtainEvent(viewEvent: PermissionsEvent) {
        when (viewEvent) {
            PermissionsEvent.NotGranted -> viewState.update { PermissionsViewState.NotGranted }
            PermissionsEvent.DismissPermission -> viewState.update { PermissionsViewState.PermissionDismissed }
        }
    }
}