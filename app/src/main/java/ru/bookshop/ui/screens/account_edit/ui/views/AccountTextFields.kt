package ru.bookshop.ui.screens.account_edit.ui.views

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.bookshop.R
import ru.bookshop.data.models.AccountDTO
import ru.bookshop.ui.theme.BookshopTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTextFields(
    info: AccountDTO,
) {
    val name = remember { mutableStateOf(info.name) }
    val job = remember { mutableStateOf(info.job) }
    val resumeUrl = remember { mutableStateOf(info.resumeUrl) }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var showImagePickerDialog by remember { mutableStateOf(false) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        selectedBitmap = bitmap
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (showImagePickerDialog) {
            BasicAlertDialog(
                onDismissRequest = { showImagePickerDialog = false },
                modifier = Modifier.background(Color.White),
                content = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("Выберите действие")

                        Spacer(modifier = Modifier.size(16.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = {
                                showImagePickerDialog = false
                                galleryLauncher.launch("image/*")
                            }) {
                                Text("Галерея")
                            }
                            Button(onClick = {
                                showImagePickerDialog = false
                                cameraLauncher.launch(null)
                            }) {
                                Text("Камера")
                            }
                        }
                    }
                },
            )
        }

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(50))
                .clickable { showImagePickerDialog = true },
            contentAlignment = Alignment.Center
        ) {
            if (selectedBitmap != null) {
                Image(
                    bitmap = selectedBitmap!!.asImageBitmap(),
                    contentDescription = "Profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else if (selectedImageUri != null) {
                AsyncImage(
                    model = selectedImageUri,
                    contentDescription = "Profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    painter = painterResource(info.image ?: R.drawable.no_photo),
                    contentDescription = "Profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

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
        )
    }
}