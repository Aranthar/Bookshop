package ru.bookshop.ui.screens.account.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bookshop.R
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun AccountTitle(
    info: AccountDTO,
    onEditClick: (ArrayList<String>) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
    ) {
        Text(
            text = "Профиль",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp),
        )



        IconButton(onClick = {
            val list = arrayListOf(info.name, info.job, info.resumeUrl.toString())

            onEditClick(list)
        }) {
            Icon(
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = "edit profile"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountTitle() {
    BookshopTheme {
        AccountTitle(
            info = AccountDTO(),
            onEditClick = {},
        )
    }
}