package ru.bookshop.ui.screens.books.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.bookshop.data.models.BookDTO
import ru.bookshop.repository.RepositoryApi
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.books.models.BooksEvent
import ru.bookshop.ui.screens.books.models.BooksState
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor() :
    BaseViewModel<BooksState, BooksEvent>(initialState = BooksState()) {
    private var repository: RepositoryApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        repository = RepositoryApi(client)
    }

    private val _books = MutableStateFlow<List<BookDTO>>(emptyList())
    val books: StateFlow<List<BookDTO>> = _books.asStateFlow()

    fun fetchBooks() {
        viewModelScope.launch {
            val books = repository.api.getBooks()
            _books.value = books
        }
    }
}