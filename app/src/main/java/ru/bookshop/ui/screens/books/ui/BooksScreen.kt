package ru.bookshop.ui.screens.books.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.bookshop.data.models.BookDTO
import ru.bookshop.ui.screens.books.presentation.BooksViewModel
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun BooksScreen(
    onDetailsScreen: (id: Int) -> Unit,
) {
    val booksViewModel = hiltViewModel<BooksViewModel>()
    val books = booksViewModel.books.collectAsState()

    LaunchedEffect(Unit) {
        booksViewModel.fetchBooks()
    }

    BookList(books.value, onDetailsScreen)
}

@SuppressLint("SwitchIntDef")
@Composable
fun BookList(
    books: List<BookDTO>,
    onDetailsScreen: (id: Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(books) { book ->
            BookCell(
                info = book,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clickable { onDetailsScreen(book.index) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookScreenPreview() {
    val bookDTO = BookDTO(
        title = "Экстремальное программирование: разработка через тестирование",
        cover = "https://raw.githubusercontent.com/fedeperin/potterapi/main/public/images/covers/1.png",
        author = listOf("Бек Кент", "Олег Павлов"),
    )
    val books = List(8) { bookDTO }

    BookshopTheme {
        BookList(
            books = books ,
            onDetailsScreen = {},
        )
    }
}