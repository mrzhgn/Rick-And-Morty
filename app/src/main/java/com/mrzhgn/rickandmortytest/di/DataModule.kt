package com.mrzhgn.rickandmortytest.di

import android.app.Application
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava3.RxDataStore
import androidx.room.Room
import com.mrzhgn.rickandmortytest.db.AppDataBase
import com.mrzhgn.rickandmortytest.db.PersonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRxPreferencesDataStore(application: Application): RxDataStore<Preferences> =
        RxPreferenceDataStoreBuilder(application, "pages").build()

    @Provides
    @Singleton
    fun provideAppDataBase(application: Application): AppDataBase = Room.databaseBuilder(
            application,
            AppDataBase::class.java,
            "person_database"
        ).build()

    @Provides
    @Singleton
    fun providePersonDao(dataBase: AppDataBase): PersonDao = dataBase.personDao()
}