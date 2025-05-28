package com.pdmcourse.spotlyfe.domain.repository

import com.pdmcourse.spotlyfe.domain.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    fun getAllPlaces(): Flow<List<Place>>
    suspend fun insertPlace(place: Place)
}
