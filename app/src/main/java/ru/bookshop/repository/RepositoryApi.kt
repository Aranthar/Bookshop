package ru.bookshop.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryApi(client: OkHttpClient) {
    val api: BookApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://potterapi-fedeperin.vercel.app/"
    }
}