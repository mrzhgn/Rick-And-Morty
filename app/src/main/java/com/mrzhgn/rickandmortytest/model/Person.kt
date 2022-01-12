package com.mrzhgn.rickandmortytest.model

data class Person(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Location,
    var location: Location,
    var image: String,
    var episode: List<String>,
    var url: String,
    var created: String
)
