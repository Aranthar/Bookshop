package ru.bookshop.ui.screens.details.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.bookshop.data.models.BookDTO
import ru.bookshop.repository.RepositoryApi
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.details.models.DetailsEvent
import ru.bookshop.ui.screens.details.models.DetailsState
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() :
    BaseViewModel<DetailsState, DetailsEvent>(initialState = DetailsState()) {
    private var repository: RepositoryApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        repository = RepositoryApi(client)
    }

    private val _bookDetails = MutableStateFlow<BookDTO?>(null)
    val bookDetails: StateFlow<BookDTO?> = _bookDetails.asStateFlow()

    fun fetchBookDetails(bookId: Int) {
        viewModelScope.launch {
            val book = repository.api.getBookDetails(bookId)
            _bookDetails.value = book
        }
    }
}