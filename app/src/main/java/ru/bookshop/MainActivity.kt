package ru.bookshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.bookshop.data.models.BookDTO
import ru.bookshop.ui.screens.books.ui.BooksScreen
import ru.bookshop.ui.theme.BookshopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookshopTheme {
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
        }
    }
}