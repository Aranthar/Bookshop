package ru.bookshop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.bookshop.data.db.entities.BooksEntity
import ru.bookshop.data.db.entities.FavoritesEntity

@Database(
    entities = [
        FavoritesEntity::class,
        BooksEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BookshopDatabase : RoomDatabase() {
    abstract fun dao(): Dao
}