package com.mrzhgn.rickandmortytest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
class PersonEntity(
    @PrimaryKey var id: Int,
    var page: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var originName: String,
    var originId: Int,
    var locationName: String,
    var locationType: String?,
    var locationDimension: String?,
    var locationId: Int,
    var image: String,
    var episodes: Int,
    var url: String,
    var created: String
)