package ru.bookshop.data.models

data class CharacteristicsDTO(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val imageId: Int,
    val author: String = "",
    var price: Int = 0,
    var grade: Float = 5f,
    val publishingHouse: String = "",
    val series: String = "",
    val publicationYear: Int,
    val pagesNumber: Int = 0,
)