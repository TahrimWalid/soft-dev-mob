package com.animereview.app

// Get sample anime reviews to display in the app
fun getSampleReviews(): MutableList<AnimeReview> = mutableListOf(
    AnimeReview(
        id = "1",
        title = "Jujutsu Kaisen",
        rating = 5,
        review = "Really good anime. Great fights and characters. Animation is amazing.",
        posterImageId = R.drawable.jujutsu_kaisen,
        isFavorite = true,
        genre = "Action, Dark Fantasy"
    ),
    AnimeReview(
        id = "2",
        title = "Demon Slayer",
        rating = 5,
        review = "Beautiful animation and sad story. I really liked watching this one.",
        posterImageId = R.drawable.demon_slayer,
        isFavorite = true,
        genre = "Action, Supernatural"
    ),
    AnimeReview(
        id = "3",
        title = "My Hero Academia",
        rating = 4,
        review = "Cool superpowers and good story. Some parts were kinda slow but still good.",
        posterImageId = R.drawable.my_hero_academia,
        isFavorite = false,
        genre = "Action, School"
    ),
    AnimeReview(
        id = "4",
        title = "Frieren: Beyond Journey's End",
        rating = 5,
        review = "Best anime ever. Nice story and beautiful drawings.",
        posterImageId = R.drawable.frieren,
        isFavorite = true,
        genre = "Fantasy, Adventure"
    ),
    AnimeReview(
        id = "5",
        title = "Solo Leveling",
        rating = 4,
        review = "Cool animation and interesting power system. Main character is awesome.",
        posterImageId = R.drawable.solo_leveling,
        isFavorite = false,
        genre = "Action, Supernatural"
    ),
    AnimeReview(
        id = "6",
        title = "Steins;Gate",
        rating = 5,
        review = "Mind bending story about time travel. Really good plot twists.",
        posterImageId = R.drawable.steins_gate,
        isFavorite = true,
        genre = "Sci-Fi, Thriller"
    ),
    AnimeReview(
        id = "7",
        title = "Violet Evergarden",
        rating = 5,
        review = "Very emotional and pretty. Made me cry a few times.",
        posterImageId = R.drawable.violet_evergarden,
        isFavorite = true,
        genre = "Drama, Fantasy"
    ),
    AnimeReview(
        id = "8",
        title = "Horimiya",
        rating = 4,
        review = "Nice romance anime. Cute and funny. Worth watching.",
        posterImageId = R.drawable.horimiya,
        isFavorite = false,
        genre = "Romance, Comedy"
    ),
    AnimeReview(
        id = "9",
        title = "Blue Lock",
        rating = 4,
        review = "Sports anime about soccer. Intense matches and good characters.",
        posterImageId = R.drawable.blue_lock,
        isFavorite = false,
        genre = "Sports, School"
    ),
    AnimeReview(
        id = "10",
        title = "Kuroko no Basket",
        rating = 4,
        review = "Basketball anime with cool characters and exciting games.",
        posterImageId = R.drawable.kuroko_no_basket,
        isFavorite = false,
        genre = "Sports, School"
    )
)
