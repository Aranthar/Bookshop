package ru.bookshop.repository

import retrofit2.http.GET
import retrofit2.http.Query
import ru.bookshop.data.models.BookDTO

interface BookApi {
    @GET("en/books")
    suspend fun getBooks(
        @Query("limit") limit: Int = 10
    ): List<BookDTO>

    @GET("en/books")
    suspend fun getBookDetails(@Query("index") bookId: Int): BookDTO
}
