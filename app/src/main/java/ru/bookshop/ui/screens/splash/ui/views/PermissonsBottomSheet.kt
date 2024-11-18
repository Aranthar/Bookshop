package ru.bookshop.ui.screens.splash.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.bookshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionsBottomSheet(onBackPressed: () -> Unit, onOpenAppSettings: @Composable () -> Unit) {
    val sheetState = rememberModalBottomSheetState(confirmValueChange = { it != SheetValue.Hidden })
    var isOpenAppSettings by remember { mutableStateOf(false) }

    ModalBottomSheet(onDismissRequest = onBackPressed, sheetState = sheetState) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.allow_access_to_gallery))
            Button(modifier = Modifier.padding(
                top = 30.dp,
                bottom = 12.dp,
            ), onClick = {
                isOpenAppSettings = true
            }) {
                Text(text = stringResource(id = R.string.to_settings_bnt))
            }
        }
    }

    if (isOpenAppSettings) {
        onOpenAppSettings()
        isOpenAppSettings = false
    }
}