package com.animereview.app

data class AnimeReview(
    val id: String = "",
    val title: String = "",
    val rating: Int = 5,
    val review: String = "",
    val posterImageId: Int = 0,
    val isFavorite: Boolean = false,
    val genre: String = ""
)
