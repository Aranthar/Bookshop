package ru.bookshop.ui.screens.account_edit.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bookshop.R
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun AccountTextFields(
    info: AccountDTO,
    onImageClick: () -> Unit,
) {
    val name = remember { mutableStateOf(info.name) }
    val job = remember { mutableStateOf(info.job) }
    val resumeUrl = remember { mutableStateOf(info.resumeUrl) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(info.image ?: R.drawable.no_photo),
            contentDescription = "Profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(50))
                .clickable(onClick = onImageClick),
        )

        Spacer(modifier = Modifier.size(16.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it }
        )

        Spacer(modifier = Modifier.size(16.dp))

        OutlinedTextField(
            value = job.value,
            onValueChange = { job.value = it }
        )

        Spacer(modifier = Modifier.size(16.dp))

        OutlinedTextField(
            value = resumeUrl.value ?: "Ссылка на резюме",
            onValueChange = { resumeUrl.value = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountContent() {
    BookshopTheme {
        AccountTextFields(
            info = AccountDTO(),
            onImageClick = {},
        )
    }
}