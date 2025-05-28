package com.pdmcourse.spotlyfe.data

import com.pdmcourse.spotlyfe.data.database.dao.PlaceDao
import com.pdmcourse.spotlyfe.data.database.entities.PlaceEntity
import com.pdmcourse.spotlyfe.domain.model.Place
import com.pdmcourse.spotlyfe.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaceRepositoryImpl(private val dao: PlaceDao) : PlaceRepository {
    override fun getAllPlaces(): Flow<List<Place>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun insertPlace(place: Place) {
        dao.insert(place.toEntity())
    }
}

fun PlaceEntity.toDomain() = Place(name, remark, latitude, longitude)
fun Place.toEntity() = PlaceEntity(name = name, remark = remark, latitude = latitude, longitude = longitude)
