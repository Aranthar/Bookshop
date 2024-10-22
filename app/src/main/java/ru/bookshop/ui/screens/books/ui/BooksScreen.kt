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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bookshop.R
import ru.bookshop.data.models.BookDTO
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun BooksScreen(
    books: List<BookDTO>,
    onDetailsScreen: () -> Unit,
) {
    BookList(books, onDetailsScreen)
}

@SuppressLint("SwitchIntDef")
@Composable
fun BookList(
    books: List<BookDTO>,
    onDetailsScreen: () -> Unit,
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
                    .clickable { onDetailsScreen() },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookScreenPreview() {
    val bookDTO = BookDTO(
        title = "Экстремальное программирование: разработка через тестирование",
        imageId = R.drawable.book,
        author = "Бек Кент",
        price = 1500,
        grade = 3.5f,
    )
    val books = List(6) { bookDTO }

    BookshopTheme {
        BooksScreen(
            books = books,
            onDetailsScreen = {},
        )
    }
}