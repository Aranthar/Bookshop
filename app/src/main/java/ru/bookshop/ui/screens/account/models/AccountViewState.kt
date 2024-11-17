package ru.bookshop.ui.screens.account.models

import ru.bookshop.data.models.AccountDTO

data class AccountViewState(
    val accountInfo: AccountDTO = AccountDTO(),
)