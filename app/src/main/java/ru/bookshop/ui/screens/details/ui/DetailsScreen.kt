package ru.bookshop.ui.screens.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.bookshop.R
import ru.bookshop.data.models.BookDTO
import ru.bookshop.ui.screens.books.ui.BookCell
import ru.bookshop.ui.screens.details.presentation.DetailsViewModel
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun DetailsScreen(
    id: Int,
    onBackClick: () -> Unit,
) {
    val bookViewModel = hiltViewModel<DetailsViewModel>()
    val book = bookViewModel.bookDetails.collectAsState()

    LaunchedEffect(Unit) {
        bookViewModel.fetchBookDetails(id)
    }

    Details(
        book = book.value,
        onBackClick = onBackClick,
    )
}

@Composable
fun Details(
    book: BookDTO?,
    onBackClick: () -> Unit,
) {
    if (book != null) {
        Column(
            Modifier.padding(
                start = 12.dp,
                end = 12.dp,
                bottom = 16.dp,
            )
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = null,
                    tint = Color.Black,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            BookCell(
                info = BookDTO(
                    title = book.title,
                    cover = "",
                    author = emptyList(),
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Characteristics(book)
        }
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Загрузка...")
        }
    }
}

@Composable
fun Characteristics(book: BookDTO) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Описание",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = book.description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Характеристики",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "Год выпуска",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Количество страниц",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = book.releaseDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = book.pages.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookCellPreview() {
    val book = BookDTO(
        title = "Гарри Поттер и узник Аскобана",
        description = "Книга про Гарри Поттера",
        cover = "https://raw.githubusercontent.com/fedeperin/potterapi/main/public/images/covers/1.png",
        releaseDate = "17 Jun, 1994",
        pages = 334,
        author = listOf("Дж.К. Роулинг")
    )
    BookshopTheme {
        Details(
            book = book,
            onBackClick = {},
        )
    }
}