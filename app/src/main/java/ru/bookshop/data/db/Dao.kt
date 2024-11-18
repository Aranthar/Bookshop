package ru.bookshop.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.bookshop.data.db.entities.BooksEntity
import ru.bookshop.data.db.entities.FavoritesEntity

@Dao
interface Dao {
    @Insert(entity = BooksEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewBook(account: BooksEntity)

    @Insert(entity = FavoritesEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewFavoriteBook(account: FavoritesEntity)

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<BooksEntity>

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavoritesBooks(): List<FavoritesEntity>
}