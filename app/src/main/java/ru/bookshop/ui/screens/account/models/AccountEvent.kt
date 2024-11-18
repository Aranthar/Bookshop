package ru.bookshop.ui.screens.account.models

import ru.bookshop.data.models.AccountDTO

sealed class AccountEvent {
    data class GetNewData(val data: AccountDTO): AccountEvent()
}