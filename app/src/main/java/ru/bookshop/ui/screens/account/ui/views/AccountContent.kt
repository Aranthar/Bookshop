package ru.bookshop.ui.screens.account.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bookshop.R
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun AccountContent(
    image: Int?,
    name: String,
    job: String,
    onResumeClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(image ?: R.drawable.no_photo),
            contentDescription = "Profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(50)),
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp),
        )

        Text(
            text = job,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = onResumeClick,
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledContentColor = Color.White,
                disabledContainerColor = MaterialTheme.colorScheme.onSecondaryContainer,
            )
        ) {
            Text(
                text = "Резюме",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountContent() {
    val info = AccountDTO()

    BookshopTheme {
        AccountContent(
            image = info.image,
            name = info.name,
            job = info.job,
            onResumeClick = {},
        )
    }
}