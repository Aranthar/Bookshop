package ru.bookshop.ui.screens.books.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.bookshop.data.db.DatabaseRepository
import ru.bookshop.data.models.BookDTO
import ru.bookshop.repository.RepositoryApi
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.books.models.BooksEvent
import ru.bookshop.ui.screens.books.models.BooksState
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val cache: DatabaseRepository,
) : BaseViewModel<BooksState, BooksEvent>(initialState = BooksState()) {

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
            val books: List<BookDTO> = try {
                repository.api.getBooks()
            } catch (e: Exception) {
                emptyList()
            }


            if (books.isNotEmpty()) {
                _books.value = books

                books.forEach { book ->
                    cache.insertNewBook(
                        BookDTO(
                            index = book.index,
                            title = book.title,
                            description = book.description,
                            cover = book.cover,
                            releaseDate = book.releaseDate,
                            pages = book.pages,
                            author = "Дж. К. Роулинг",
                        )
                    )
                }
            } else {
                val cacheBooks = cache.getAllBooks()
                _books.value = cacheBooks
            }
        }
    }
}