package ru.bookshop.ui.screens.details.models

import ru.bookshop.data.models.BookDTO

data class DetailsState(
    val characteristics: BookDTO? = null
)