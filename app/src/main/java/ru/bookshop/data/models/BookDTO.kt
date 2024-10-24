package ru.bookshop.data.models

data class BookDTO(
    val id: Int = 0,
    val title: String = "",
    val imageId: Int,
    val author: String = "",
    var price: Int = 0,
    var grade: Float = 5f,
) : DTO()