package com.mrzhgn.rickandmortytest.model

data class PagesResponse(
    var info: Info,
    var results: List<Person>,
)
