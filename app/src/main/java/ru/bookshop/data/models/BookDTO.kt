package ru.bookshop.data.models

data class BookDTO(
    val title: String,
    val author: List<String>?,
    val imageId: String?,
) : DTO()