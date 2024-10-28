package ru.bookshop.data.models

data class BookDTO(
    val number: Int = 0,
    val title: String,
    val description: String = "",
    val cover: String?,
    val releaseDate: String = "",
    val pages: Int = 0,
    val author: List<String>?,
) : DTO()