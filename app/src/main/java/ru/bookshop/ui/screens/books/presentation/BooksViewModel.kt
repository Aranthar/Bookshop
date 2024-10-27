package ru.bookshop.ui.screens.books.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.bookshop.data.models.BookDTO
import ru.bookshop.repository.RepositoryInstance
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.books.models.BooksEvent
import ru.bookshop.ui.screens.books.models.BooksState
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor() :
    BaseViewModel<BooksState, BooksEvent>(initialState = BooksState()) {

    private val _books = MutableStateFlow<List<BookDTO>>(emptyList())
    val books: StateFlow<List<BookDTO>> = _books.asStateFlow()

    fun fetchBooks() {
        viewModelScope.launch {
            RepositoryInstance.api.getBooks()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _books.value = listOf(BookDTO(
                        id = -1,
                        title = e.toString(),
                        imageId = 0,
                    ))
                    Log.e("Retrofit", e.toString())
                }
                .collect { bookList ->
                    _books.value = bookList
                }
        }
    }
}