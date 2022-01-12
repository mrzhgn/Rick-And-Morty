package com.mrzhgn.rickandmortytest.repository

import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import com.mrzhgn.rickandmortytest.db.PersonDao
import com.mrzhgn.rickandmortytest.db.PersonEntity
import com.mrzhgn.rickandmortytest.network.ApiService
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Repository @Inject constructor(private val dataStore: RxDataStore<Preferences>, private val api: ApiService, private val db: PersonDao) {

    companion object {
        private val REPOSITORY_TAG = Repository::class.java.simpleName
        val PAGES_COUNT = intPreferencesKey("pages_count")
    }

    fun getPage(page: Int): Single<List<PersonEntity>> = getLocalPagesCount()
            .defaultIfEmpty(0)
            .onErrorResumeNext { Single.just(0) }
            .flatMap { if (it >= page) {
                Log.d(REPOSITORY_TAG, "Loaded from DataBase")
                db.getPage(page)
            }
            else {
                Log.d(REPOSITORY_TAG, "Loaded from Network")
                api.getPage(page).map { list ->
                    list.results.map { person ->
                    PersonEntity(
                        person.id, page, person.name, person.status,
                        person.species, person.type, person.gender,
                        person.origin.name, getIdFromUrl(person.origin.url),
                        person.location.name, null, null,
                        getIdFromUrl(person.location.url), person.image,
                        person.episode.size, person.url, person.created
                    )
                } }
                    .doOnSuccess {
                        db.insert(it)
                        setLocalPagesCount(page)
                    }
            } }

    fun getCharacterInfo(id: Int) = api.getCharacterInfo(id)

    fun getCharacterLocation(id: Int) = api.getCharacterLocation(id)

    fun getLocalPagesCount(): Maybe<Int> = dataStore.data().map { it[PAGES_COUNT] ?: 0 }.firstElement()

    fun setLocalPagesCount(count: Int) {
        dataStore.updateDataAsync { prefsIn ->
            val mutablePreferences = prefsIn.toMutablePreferences()
            mutablePreferences[PAGES_COUNT] = count
            return@updateDataAsync Single.just(mutablePreferences)
        }
    }

    fun getIdFromUrl(url: String): Int = if (!url.isEmpty()) url.split("/").last().toInt() else 0
}