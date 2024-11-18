package ru.bookshop.ui.screens.account.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.account.models.AccountEvent
import ru.bookshop.ui.screens.account.models.AccountViewState
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor() :
    BaseViewModel<AccountViewState, AccountEvent>(AccountViewState()) {

    override fun obtainEvent(viewEvent: AccountEvent) {
        when (viewEvent) {
            is AccountEvent.GetNewData -> {
                viewModelScope.launch {
                    viewState.update {
                        it.copy(
                            accountInfo = AccountDTO(
                                image = viewEvent.data.image,
                                name = viewEvent.data.name,
                                job = viewEvent.data.job,
                                resumeUrl = viewEvent.data.resumeUrl,
                            )
                        )
                    }
                }
            }
        }
    }
}