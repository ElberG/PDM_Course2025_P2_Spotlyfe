package com.pdmcourse.spotlyfe.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun SelectLocationMap(
    initialPosition: LatLng = LatLng(13.67, -89.23),
    onLocationChanged: (LatLng) -> Unit
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 16f)
    }

    var uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }

    val markerState = rememberMarkerState(position = initialPosition)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(300.dp)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            onMapClick = { newLatLng ->
                markerState.position = newLatLng
                onLocationChanged(newLatLng)
                Log.d("SelectLocationMap", "New location selected: $newLatLng")
            }
        ) {
            Marker(state = markerState)
        }
    }
}
