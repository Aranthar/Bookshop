package ru.bookshop.ui.screens.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.screens.account.ui.views.AccountContent
import ru.bookshop.ui.screens.account.ui.views.AccountTitle
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun AccountScreen(
    info: AccountDTO,
    onEditClick: () -> Unit,
    onResumeClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp, horizontal = 16.dp),
    ) {
        AccountTitle(onEditClick)
        AccountContent(
            image = info.image,
            name = info.name,
            job = info.job,
            onResumeClick = onResumeClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    BookshopTheme {
        AccountScreen(
            info = AccountDTO(),
            onEditClick = {},
            onResumeClick = {},
        )
    }
}