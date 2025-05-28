package com.pdmcourse.spotlyfe.ui.screens.Form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.pdmcourse.spotlyfe.domain.model.Place
import com.pdmcourse.spotlyfe.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val repository: PlaceRepository
) : ViewModel() {

    private val _location = MutableStateFlow(LatLng(13.67, -89.23))
    val location: StateFlow<LatLng> = _location

    fun updateLocation(newLocation: LatLng) {
        _location.value = newLocation
    }

    fun savePlace(name: String, remark: String, onSaved: () -> Unit) {
        viewModelScope.launch {
            val latLng = location.value
            repository.insertPlace(Place(name, remark, latLng.latitude, latLng.longitude))
            onSaved()
        }
    }
}
