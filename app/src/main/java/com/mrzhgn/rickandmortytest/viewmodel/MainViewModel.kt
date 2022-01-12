package com.mrzhgn.rickandmortytest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mrzhgn.rickandmortytest.db.PersonEntity
import com.mrzhgn.rickandmortytest.ui.DataLoadingState
import com.mrzhgn.rickandmortytest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository): ViewModel() {

    companion object {
        private val API_CALL_TAG = Repository::class.java.simpleName
    }

    private val composite = CompositeDisposable()

    private val pageLiveData = MutableLiveData(1)
    val personLiveData = MutableLiveData<PersonEntity>()
    val listLiveData: LiveData<List<PersonEntity>>
    init {
        listLiveData = Transformations.switchMap(pageLiveData) {
            getAllPages(it)
        }
    }

    val loadingStateLiveData = MutableLiveData<DataLoadingState>()

    fun incrementPage() {
        pageLiveData.value?.let { pageLiveData.value = it + 1 }
    }

    fun getAllPages(page: Int): LiveData<List<PersonEntity>> {
        loadingStateLiveData.value = DataLoadingState.LOADING
        val currentList: MutableList<PersonEntity> = mutableListOf()
        listLiveData.value?.let { currentList.addAll(it) }
        val newLiveData = MutableLiveData<List<PersonEntity>>()
        composite += repository.getPage(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    currentList.addAll(it)
                    newLiveData.value = currentList
                    loadingStateLiveData.value = DataLoadingState.LOADED
                },
                { error ->
                    Log.e(API_CALL_TAG, error.toString())
                    loadingStateLiveData.value = DataLoadingState.ERROR
                }
            )
        return newLiveData
    }

    fun getCharacterInfo(id: Int) {
        composite += repository.getCharacterInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { person ->
                    personLiveData.value = person
                        .let { PersonEntity(
                            it.id, 0, it.name, it.status, it.species,
                            it.type, it.gender, it.origin.name, repository.getIdFromUrl(it.origin.url),
                            it.location.name, null, null,
                            repository.getIdFromUrl(it.location.url), it.image,
                            it.episode.size, it.url, it.created
                        ) }
                        .apply { getCharacterLocation(this) }
                },
                {
                    Log.e(API_CALL_TAG, it.toString())
                }
            )
    }

    fun getCharacterLocation(person: PersonEntity) {
        if (person.locationId != 0) {
            composite += repository.getCharacterLocation(person.locationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        person.locationType = it.type
                        person.locationDimension = it.dimension
                        personLiveData.value = person
                    },
                    {
                        Log.e(API_CALL_TAG, it.toString())
                    }
                )
        }
    }

    override fun onCleared() {
        super.onCleared()
        composite.dispose()
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable){
        this.add(disposable)
    }
}