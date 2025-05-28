package com.pdmcourse.spotlyfe.ui.screens.SavedPlaces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.ui.layout.CustomFloatingButton
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar
import androidx.compose.ui.unit.dp

@Composable
fun SavedPlacesScreen(viewModel: SavedPlacesViewModel, onAddClick: () -> Unit) {
  val places by viewModel.places.collectAsState()

  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(LatLng(13.679024407659101, -89.23578718993471), 16f)
  }

  Scaffold(
    topBar = { CustomTopBar() },
    floatingActionButton = {
      androidx.compose.foundation.layout.Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(start = 32.dp, bottom = 18.dp),
        contentAlignment = androidx.compose.ui.Alignment.BottomStart
      ) {
        CustomFloatingButton(onClick = onAddClick)
      }
    }
  ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {
      GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.HYBRID),
        uiSettings = MapUiSettings(zoomControlsEnabled = true)
      ) {
        places.forEach {
          Marker(
            state = MarkerState(position = LatLng(it.latitude, it.longitude)),
            title = it.name,
            snippet = it.remark
          )
        }
      }
    }
  }
}

