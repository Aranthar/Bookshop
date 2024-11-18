package ru.bookshop.ui.screens.books.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import ru.bookshop.R
import ru.bookshop.data.models.BookDTO
import ru.bookshop.ui.theme.BookshopTheme

@SuppressLint("DefaultLocale")
@Composable
fun BookCell(
    info: BookDTO,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(info.cover)
                .crossfade(true)
                .build(),
            contentDescription = "Book cover",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(180.dp),
            contentScale = ContentScale.None,
            onError = {
                Log.e("Coil", "Failed to load image: ${it.result.throwable}")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp),
        ) {
            val grade = 3.5
            var half = false

            repeat(5) { index ->
                val icon = when {
                    grade >= index + 1 -> R.drawable.star
                    grade % 1f >= 0.5f-> {
                        if (!half) {
                            half = true
                            R.drawable.star_half
                        } else {
                            R.drawable.star_border
                        }
                    }
                    else -> R.drawable.star_border
                }
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = Color(0xFFF5C01C),
                    modifier = Modifier.size(20.dp),
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "$grade / 5", style = MaterialTheme.typography.bodyMedium)
        }

        Text(
            text = info.title,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 4.dp),
        )

        Text(
            text = info.author ?: "Дж. К. Роулинг",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookCellPreview() {
    BookshopTheme {
        BookCell(
            info = BookDTO(
                title = "Экстремальное программирование: разработка через тестирование",
                cover = "https://ap.auezov.edu.kz/images/news/aaa001.png",
                author = "Дж. К. Роулинг",
            ),
        )
    }
}