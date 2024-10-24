package ru.bookshop.ui.screens.details.models

import ru.bookshop.R
import ru.bookshop.data.models.BookDTO
import ru.bookshop.data.models.CharacteristicsDTO

data class DetailsState(
    val characteristics: CharacteristicsDTO = CharacteristicsDTO(
        imageId = R.drawable.book,
        publicationYear = 2024,
    ),
)