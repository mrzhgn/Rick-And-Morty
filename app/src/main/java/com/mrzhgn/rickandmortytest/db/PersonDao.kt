package com.mrzhgn.rickandmortytest.db

import androidx.room.*
import io.reactivex.rxjava3.core.Single

@Dao
interface PersonDao {

    @Query("SELECT * from person_table WHERE page = :page")
    fun getPage(page: Int): Single<List<PersonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(personEntity: PersonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<PersonEntity>)

    @Update
    fun update(personEntity: PersonEntity)

    @Update
    fun update(list: List<PersonEntity>)

    @Delete
    fun delete(personEntity: PersonEntity)

    @Delete
    fun delete(list: List<PersonEntity>)
}