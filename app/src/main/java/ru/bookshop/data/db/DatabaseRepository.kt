package ru.bookshop.data.db

import ru.bookshop.data.db.entities.BooksEntity
import ru.bookshop.data.db.entities.FavoritesEntity
import ru.bookshop.data.models.BookDTO
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val dao: Dao
) {
    suspend fun insertNewBook(book: BookDTO) = dao.insertNewBook(
        BooksEntity(
            id = book.index,
            index = book.index,
            title = book.title,
            description = book.description,
            cover = book.cover,
            releaseDate = book.releaseDate,
            pages = book.pages,
            author = "Дж. К. Роулинг",
        )
    )

    suspend fun insertNewBookFavoriteBook(book: BookDTO) = dao.insertNewFavoriteBook(
        FavoritesEntity(
            id = book.index,
            index = book.index,
            title = book.title,
            description = book.description,
            cover = book.cover,
            releaseDate = book.releaseDate,
            pages = book.pages,
            author = "Дж. К. Роулинг",
        )
    )

    suspend fun getAllBooks(): List<BookDTO> {
        val list = mutableListOf<BookDTO>()

        dao
            .getAllBooks()
            .forEach { book ->
                list.add(
                    BookDTO(
                        index = book.index,
                        title = book.title,
                        description = book.description,
                        cover = book.cover,
                        releaseDate = book.releaseDate,
                        pages = book.pages,
                        author = book.author,
                    )
                )
            }

        return list
    }

    suspend fun getAllFavoritesBooks(): List<BookDTO> {
        val list = mutableListOf<BookDTO>()

        dao
            .getAllFavoritesBooks()
            .forEach { book ->
                list.add(
                    BookDTO(
                        index = book.index,
                        title = book.title,
                        description = book.description,
                        cover = book.cover,
                        releaseDate = book.releaseDate,
                        pages = book.pages,
                        author = book.author,
                    )
                )
            }

        return list
    }
}