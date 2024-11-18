package ru.bookshop.ui.screens.account_edit.ui

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
import ru.bookshop.ui.screens.account_edit.ui.views.AccountEditTitle
import ru.bookshop.ui.screens.account_edit.ui.views.AccountTextFields
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun AccountEditScreen(
    info: AccountDTO,
    onDoneClick: () -> Unit,
    onBackClick: () -> Unit,
    onImageClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp, horizontal = 16.dp),
    ) {
        AccountEditTitle(
            onBackClick = onBackClick,
            onDoneClick = onDoneClick,
        )
        AccountTextFields(
            info = info,
            onImageClick = onImageClick,
        )
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
            onImageClick = {},
        )
    }
}