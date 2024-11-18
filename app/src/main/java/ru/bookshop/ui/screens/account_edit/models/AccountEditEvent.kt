package ru.bookshop.ui.screens.account_edit.models

import ru.bookshop.data.models.AccountDTO

sealed class AccountEditEvent {
    data class OnDoneClick(val info: AccountDTO): AccountEditEvent()
}