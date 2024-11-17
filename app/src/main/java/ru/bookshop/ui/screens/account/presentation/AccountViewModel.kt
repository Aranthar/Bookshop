package ru.bookshop.ui.screens.account.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.account.models.AccountEvent
import ru.bookshop.ui.screens.account.models.AccountViewState
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor() :
    BaseViewModel<AccountViewState, AccountEvent>(AccountViewState()) {

        private val accountInfo = AccountDTO()
    
}