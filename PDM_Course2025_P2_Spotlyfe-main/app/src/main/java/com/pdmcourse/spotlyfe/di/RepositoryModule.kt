package com.pdmcourse.spotlyfe.di

import android.content.Context
import com.pdmcourse.spotlyfe.data.PlaceRepositoryImpl
import com.pdmcourse.spotlyfe.data.database.AppDatabase
import com.pdmcourse.spotlyfe.domain.repository.PlaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun providePlaceDao(db: AppDatabase) = db.placeDao()

    @Provides
    @Singleton
    fun providePlaceRepository(placeDao: com.pdmcourse.spotlyfe.data.database.dao.PlaceDao): PlaceRepository {
        return PlaceRepositoryImpl(placeDao)
    }
}
