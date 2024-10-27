package ru.bookshop.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryInstance {
    private const val BASE_URL = "https://example.com/api/"

    val api: BookApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApi::class.java)
    }
}