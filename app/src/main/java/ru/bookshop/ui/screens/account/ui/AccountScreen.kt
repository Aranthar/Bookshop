package ru.bookshop.ui.screens.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.screens.account.models.AccountEvent
import ru.bookshop.ui.screens.account.presentation.AccountViewModel
import ru.bookshop.ui.screens.account.ui.views.AccountContent
import ru.bookshop.ui.screens.account.ui.views.AccountTitle
import ru.bookshop.ui.theme.BookshopTheme

@Composable
fun AccountScreen(
    newInfo: AccountDTO? = null,
    viewModel: AccountViewModel = hiltViewModel(),
    onEditClick: (ArrayList<String>) -> Unit,
) {
    val viewState by viewModel
        .getViewState()
        .collectAsStateWithLifecycle()

    if (newInfo != null) {
        viewModel.obtainEvent(
            AccountEvent.GetNewData(
                AccountDTO(
                    image = newInfo.image,
                    name = newInfo.name,
                    job = newInfo.job,
                    resumeUrl = newInfo.resumeUrl,
                )
            )
        )
    }

    val info = viewState.accountInfo

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        AccountTitle(
            info = info,
            onEditClick = onEditClick,
        )
        AccountContent(
            image = info.image,
            name = info.name,
            job = info.job,
            onResumeClick = {

            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    BookshopTheme {
        AccountScreen(
            onEditClick = {},
        )
    }
}