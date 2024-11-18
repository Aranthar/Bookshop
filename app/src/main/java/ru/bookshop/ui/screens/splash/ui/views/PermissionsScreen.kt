package ru.bookshop.ui.screens.splash.ui.views

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import ru.bookshop.ui.screens.splash.models.PermissionsEvent
import ru.bookshop.ui.screens.splash.models.PermissionsViewState
import ru.bookshop.ui.screens.splash.presentation.PermissionsViewModel

@Composable
fun PermissionsScreen(
    viewModel: PermissionsViewModel,
    onBackPressed: () -> Unit,
    onPermissionsGranted: () -> Unit,
) {
    val viewState by viewModel.getViewState().collectAsStateWithLifecycle()
    val lifecycleState by LocalLifecycleOwner.current.lifecycle.currentStateFlow.collectAsState()
    val context = LocalContext.current

    val checkPermission = {
        checkPermission(context = context,
            onGranted = onPermissionsGranted,
            onNotGranted = { viewModel.obtainEvent(PermissionsEvent.NotGranted) })
    }
    when (viewState) {
        PermissionsViewState.CheckGranted -> checkPermission()

        PermissionsViewState.NotGranted -> {
            showPermissionDialog(
                onAccept = onPermissionsGranted,
                onDismiss = { },
            )
        }

        PermissionsViewState.PermissionDismissed -> {
            PermissionsBottomSheet(onBackPressed = onBackPressed, onOpenAppSettings = {
                openAppSettings(context)
                if (lifecycleState == Lifecycle.State.RESUMED) checkPermission()
            })
        }
    }
}

private fun checkPermission(
    context: Context,
    onGranted: () -> Unit,
    onNotGranted: () -> Unit,
) {
    val granted = ContextCompat.checkSelfPermission(
        context, Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    if (granted) onGranted() else onNotGranted()
}

private fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    context.startActivity(intent)
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun showPermissionDialog(onAccept: () -> Unit, onDismiss: () -> Unit) {
    val launcher = rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE) {
        if (it) onAccept() else onDismiss()
    }

    SideEffect { launcher.launchPermissionRequest() }
}