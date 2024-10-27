package ru.bookshop.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import ru.bookshop.data.models.BookDTO
import ru.bookshop.data.models.CharacteristicsDTO

interface BookApi {
    @GET("books")
    suspend fun getBooks(): Flow<List<BookDTO>>

    @GET("books/{id}")
    suspend fun getBookDetails(@Path("id") bookId: Int): Flow<CharacteristicsDTO>
}
