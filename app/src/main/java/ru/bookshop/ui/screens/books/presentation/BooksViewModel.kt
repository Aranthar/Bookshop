package ru.bookshop.ui.screens.books.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.bookshop.data.models.BookDTO
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.books.models.BooksEvent
import ru.bookshop.ui.screens.books.models.BooksState
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor() :
    BaseViewModel<BooksState, BooksEvent>(initialState = BooksState()) {

    private var booksList: List<BookDTO>? = listOf()
}