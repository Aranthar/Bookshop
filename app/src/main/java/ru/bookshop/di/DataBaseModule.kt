package ru.bookshop.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.bookshop.data.db.BookshopDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BookshopDatabase {
        return Room
            .databaseBuilder(context, BookshopDatabase::class.java, "bookshop_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db: BookshopDatabase) = db.dao()
}