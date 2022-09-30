package com.kitsogideonnnamane.kitcalories.data

import java.util.UUID

data class RecipeModel(
    var id: String = UUID.randomUUID().toString(),
    var title: String,
    var description: String,
    var ingredients: List<String>,
    var thumbNailUrl: String?,
    var largeImageUrl: String?,
    var regularImageUrl: String?,
    var smallImageUrl: String?,
    var isFavorite: Boolean,
    var dietLabels: List<String>,
    var healthLabels: List<String>,
    var cautions: List<String>,
    var mealType: List<String>,
    var dishType: List<String>,
    var cuisineType: List<String>,
)
