package ru.bookshop.ui.screens.account_edit.models

import ru.bookshop.data.models.AccountDTO

data class AccountEditViewState(
    val accountInfo: AccountDTO = AccountDTO(),
)