package ru.bookshop.ui.screens.details.presentation

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
import ru.bookshop.data.models.CharacteristicsDTO
import ru.bookshop.repository.RepositoryInstance
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.details.models.DetailsEvent
import ru.bookshop.ui.screens.details.models.DetailsState
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() :
    BaseViewModel<DetailsState, DetailsEvent>(initialState = DetailsState()) {

    private val _bookDetails = MutableStateFlow<CharacteristicsDTO?>(null)
    val bookDetails: StateFlow<CharacteristicsDTO?> = _bookDetails.asStateFlow()

    fun fetchBookDetails(bookId: Int) {
        viewModelScope.launch {
            RepositoryInstance.api.getBookDetails(bookId)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _bookDetails.value = null
                    Log.e("Retrofit", e.toString())
                }
                .collect { book ->
                    _bookDetails.value = book
                }
        }
    }
}