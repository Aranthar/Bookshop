package ru.bookshop.ui.screens.details.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.bookshop.ui.common.models.BaseViewModel
import ru.bookshop.ui.screens.details.models.DetailsEvent
import ru.bookshop.ui.screens.details.models.DetailsState
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() :
    BaseViewModel<DetailsState, DetailsEvent>(initialState = DetailsState()) {

}