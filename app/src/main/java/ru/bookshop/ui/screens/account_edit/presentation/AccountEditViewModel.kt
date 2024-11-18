package ru.bookshop.ui.screens.account_edit.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.account_edit.models.AccountEditEvent
import ru.bookshop.ui.screens.account_edit.models.AccountEditViewState
import javax.inject.Inject

@HiltViewModel
class AccountEditViewModel @Inject constructor() :
    BaseViewModel<AccountEditViewState, AccountEditEvent>(AccountEditViewState()) {

    override fun obtainEvent(viewEvent: AccountEditEvent) {
        when (viewEvent) {
            is AccountEditEvent.OnDoneClick -> {
                viewModelScope.launch {
                    viewState.update {
                        it.copy(
                            accountInfo = AccountDTO(
                                image = viewEvent.info.image,
                                name = viewEvent.info.name,
                                job = viewEvent.info.job,
                                resumeUrl = viewEvent.info.resumeUrl,
                            )
                        )
                    }
                }
            }
        }
    }
}