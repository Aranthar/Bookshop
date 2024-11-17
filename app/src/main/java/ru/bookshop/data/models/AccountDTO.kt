package ru.bookshop.data.models

data class AccountDTO(
    val image: Int? = null,
    val name: String = "Имя",
    val job: String = "Должность",
    val resumeUrl: String? = null,
): DTO()
