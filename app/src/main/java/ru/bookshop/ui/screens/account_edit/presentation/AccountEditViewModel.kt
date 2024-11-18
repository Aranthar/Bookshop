package ru.bookshop.ui.screens.account_edit.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.account_edit.models.AccountEditEvent
import ru.bookshop.ui.screens.account_edit.models.AccountEditViewState
import javax.inject.Inject

@HiltViewModel
class AccountEditViewModel @Inject constructor() :
    BaseViewModel<AccountEditViewState, AccountEditEvent>(AccountEditViewState()) {

    private val accountInfo = AccountDTO()

}