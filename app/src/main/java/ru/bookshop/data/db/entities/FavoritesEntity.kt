package ru.bookshop.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesEntity(
    @PrimaryKey
    val id: Int,
    val index: Int,
    val title: String,
    val description: String,
    val cover: String,
    val releaseDate: String,
    val pages: Int,
    val author: String,
)