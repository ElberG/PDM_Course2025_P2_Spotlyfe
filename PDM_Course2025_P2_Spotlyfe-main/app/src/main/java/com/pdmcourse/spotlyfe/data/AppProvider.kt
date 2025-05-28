package com.pdmcourse.spotlyfe.data

import android.content.Context
import com.pdmcourse.spotlyfe.data.database.AppDatabase
import com.pdmcourse.spotlyfe.domain.repository.PlaceRepository

class AppProvider(context: Context) {
  private val appDatabase = AppDatabase.getDatabase(context)

  fun providePlaceRepository(): PlaceRepository {
    val placeDao = appDatabase.placeDao()
    return PlaceRepositoryImpl(placeDao)
  }
}

