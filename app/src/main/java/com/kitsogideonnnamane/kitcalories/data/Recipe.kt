package com.kitsogideonnnamane.kitcalories.data

import java.util.UUID


data class Image(
    val height: Int,
    val url: String,
    val width: Int
)

data class Images(
    val LARGE: Image,
    val REGULAR: Image,
    val SMALL: Image,
    val THUMBNAIL: Image
)

data class RecipeModel(
    var id: String = UUID.randomUUID().toString(),
    var title: String,
    var description: String,
    var ingredients: List<String>,
    var images: List<Images>,
    var isFavorite: Boolean,
    var dietLabels: List<String>,
    var healthLabels: List<String>,
    var cautions: List<String>,
    var mealType: List<String>,
    var dishType: List<String>,
    var cuisineType: List<String>,
)
