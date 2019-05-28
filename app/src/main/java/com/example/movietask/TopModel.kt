package com.example.movietask

data class TopModel(val totalPages: Int ? ,
                    val results: List<ResultsItem>?)


data class ResultsItem(val overview: String ?,
                       val originalLanguage: String ?,
                       val originalTitle: String ?,
                       val video: Boolean ?,
                       val title: String ?,
                       val genreIds: List<Int>?,
                       val posterPath: String?,
                       val backdropPath: String ?,
                       val releaseDate: String ?,
                       val voteAverage: Int ?,
                       val popularity: Double ?,
                       val id: Int ?,
                       val adult: Boolean ?,
                       val voteCount: Int ?)


