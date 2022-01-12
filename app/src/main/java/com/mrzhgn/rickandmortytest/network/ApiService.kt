package com.mrzhgn.rickandmortytest.network

import com.mrzhgn.rickandmortytest.model.Location
import com.mrzhgn.rickandmortytest.model.LocationResponse
import com.mrzhgn.rickandmortytest.model.PagesResponse
import com.mrzhgn.rickandmortytest.model.Person
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    fun getPage(
        @Query("page") page: Int
    ): Single<PagesResponse>

    @GET("location")
    fun getLocations(
        @Query("page") page: Int
    ): Single<LocationResponse>

    @GET("character/{id}")
    fun getCharacterInfo(
        @Path("id") id: Int
    ): Single<Person>

    @GET("location/{id}")
    fun getCharacterLocation(
        @Path("id") id: Int
    ): Single<Location>
}