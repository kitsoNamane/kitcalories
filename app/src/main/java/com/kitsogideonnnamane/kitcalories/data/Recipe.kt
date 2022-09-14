package com.kitsogideonnnamane.kitcalories.data

import java.util.UUID

data class Recipe(
    var title: String,
    var description: String,
    var isFavorite: Boolean,
    var id: String = UUID.randomUUID().toString()
)
