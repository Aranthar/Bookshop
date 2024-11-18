package ru.bookshop.ui.screens.account_edit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bookshop.R
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun AccountEditScreen(
    info: AccountDTO,
    onDoneClick: (ArrayList<String>) -> Unit,
    onBackClick: () -> Unit,
) {
    val name = remember { mutableStateOf(info.name) }
    val job = remember { mutableStateOf(info.job) }
    val resumeUrl = remember { mutableStateOf(info.resumeUrl) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        ) {
            IconButton(onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "go back"
                )
            }

            Text(
                text = "Редактирование",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
            )

            IconButton(onClick = {
                val list = arrayListOf(
                    name.value,
                    job.value,
                    resumeUrl.value,
                )

                onDoneClick(list)
            }) {
                Icon(
                    painter = painterResource(R.drawable.ic_done),
                    contentDescription = "done profile"
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
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

            Text(
                text = "Ссылка на файл",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = resumeUrl.value,
                onValueChange = { resumeUrl.value = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    BookshopTheme {
        AccountEditScreen(
            info = AccountDTO(),
            onDoneClick = {},
            onBackClick = {},
        )
    }
}