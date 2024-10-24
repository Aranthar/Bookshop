package ru.bookshop.ui.screens.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bookshop.R
import ru.bookshop.data.models.BookDTO
import ru.bookshop.data.models.CharacteristicsDTO
import ru.bookshop.ui.screens.books.ui.BookCell
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun DetailsScreen(
    id: Int,
    onBackClick: () -> Unit,
) {
    val info = CharacteristicsDTO(
        title = "Экстремальное программирование: разработка через тестирование",
        imageId = R.drawable.book,
        author = "Бек Кент",
        price = 1500,
        grade = 3.5f,
        publishingHouse = "Питер",
        series = "Библиотека программиста",
        publicationYear = 2024,
        pagesNumber = 224,
    )
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
                title = info.title,
                imageId = info.imageId,
                author = info.author,
                price = info.price,
                grade = info.grade,
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Characteristics(info)
    }
}

@Composable
fun Characteristics(characteristicsInfo: CharacteristicsDTO) {
    Column {
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
                    text = "Издательство",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Серия",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(8.dp))
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
                    text = characteristicsInfo.publishingHouse,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = characteristicsInfo.series,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = characteristicsInfo.publicationYear.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = characteristicsInfo.pagesNumber.toString(),
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
    BookshopTheme {
        DetailsScreen(
            id = 1,
            onBackClick = {},
        )
    }
}