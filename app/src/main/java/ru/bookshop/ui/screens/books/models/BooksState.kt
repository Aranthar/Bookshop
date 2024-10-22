package ru.bookshop.ui.screens.books.models

import ru.bookshop.data.models.BookDTO

data class BooksState(
    val booksList: List<BookDTO> = listOf(),
)