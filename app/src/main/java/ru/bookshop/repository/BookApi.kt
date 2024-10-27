package ru.bookshop.repository

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.bookshop.data.models.BookDTO
import ru.bookshop.data.models.CharacteristicsDTO

interface BookApi {
    @GET("subjects/fiction.json")
    suspend fun getBooks(
        @Query("limit") limit: Int = 30
    ): List<BookDTO>

    @GET("books/{id}")
    suspend fun getBookDetails(@Path("id") bookId: Int): CharacteristicsDTO
}
