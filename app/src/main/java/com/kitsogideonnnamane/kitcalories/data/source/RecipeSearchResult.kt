package com.kitsogideonnnamane.kitcalories.data.source

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Self(
    val href: String,
    val title: String
)

@JsonClass(generateAdapter = true)
data class LinksX(
    val next: Self
)

@JsonClass(generateAdapter = true)
data class Links(
    val self: Self?
)

@JsonClass(generateAdapter = true)
data class Ingredient(
    val food: String?,
    val foodCategory: String?,
    val foodId: String?,
    val image: String?,
    val measure: String?,
    val quantity: Double?,
    val text: String?,
    val weight: Double?
)

@JsonClass(generateAdapter = true)
data class TotalDaily(
    val CA: NutrientInfo?,
    val CHOCDF: NutrientInfo?,
    val CHOLE: NutrientInfo?,
    val ENERC_KCAL: NutrientInfo?,
    val FASAT: NutrientInfo?,
    val FAT: NutrientInfo?,
    val FE: NutrientInfo?,
    val FIBTG: NutrientInfo?,
    val FOLDFE: NutrientInfo?,
    val K: NutrientInfo?,
    val MG: NutrientInfo?,
    val NA: NutrientInfo?,
    val NIA: NutrientInfo?,
    val P: NutrientInfo?,
    val PROCNT: NutrientInfo?,
    val RIBF: NutrientInfo?,
    val THIA: NutrientInfo?,
    val TOCPHA: NutrientInfo?,
    val VITA_RAE: NutrientInfo?,
    val VITB12: NutrientInfo?,
    val VITB6A: NutrientInfo?,
    val VITC: NutrientInfo?,
    val VITD: NutrientInfo?,
    val VITK1: NutrientInfo?,
    val ZN: NutrientInfo?
)

@JsonClass(generateAdapter = true)
data class TotalNutrients(
    val CA: NutrientInfo?,
    val CHOCDF: NutrientInfo?,
    @Json(name = "CHOCDF.net")
    val NetCHOCDF: NutrientInfo?,
    val CHOLE: NutrientInfo?,
    val ENERC_KCAL: NutrientInfo?,
    val FAMS: NutrientInfo?,
    val FAPU: NutrientInfo?,
    val FASAT: NutrientInfo?,
    val FAT: NutrientInfo?,
    val FATRN: NutrientInfo?,
    val FE: NutrientInfo?,
    val FIBTG: NutrientInfo?,
    val FOLAC: NutrientInfo?,
    val FOLDFE: NutrientInfo?,
    val FOLFD: NutrientInfo?,
    val K: NutrientInfo?,
    val MG: NutrientInfo?,
    val NA: NutrientInfo?,
    val NIA: NutrientInfo?,
    val P: NutrientInfo?,
    val PROCNT: NutrientInfo?,
    val RIBF: NutrientInfo?,
    val SUGAR: NutrientInfo?,
    @Json(name = "SUGAR.added")
    val AddedSUGAR: NutrientInfo?,
    @Json(name = "Sugar.alcohol")
    val AlcoholSugar: NutrientInfo?,
    val THIA: NutrientInfo?,
    val TOCPHA: NutrientInfo?,
    val VITA_RAE: NutrientInfo?,
    val VITB12: NutrientInfo?,
    val VITB6A: NutrientInfo?,
    val VITC: NutrientInfo?,
    val VITD: NutrientInfo?,
    val VITK1: NutrientInfo?,
    val WATER: NutrientInfo?,
    val ZN: NutrientInfo?
)

@JsonClass(generateAdapter = true)
data class Sub(
    val daily: Double?,
    val hasRDI: Boolean?,
    val label: String?,
    val schemaOrgTag: String?,
    val tag: String?,
    val total: Double?,
    val unit: String?
)

@JsonClass(generateAdapter = true)
data class Image(
    val height: Int,
    val url: String,
    val width: Int
)

@JsonClass(generateAdapter = true)
data class Images(
    val LARGE: Image?,
    val REGULAR: Image?,
    val SMALL: Image?,
    val THUMBNAIL: Image?
)

@JsonClass(generateAdapter = true)
data class Digest(
    val daily: Double?,
    val hasRDI: Boolean?,
    val label: String?,
    val schemaOrgTag: String?,
    val sub: List<Sub>?,
    val tag: String?,
    val total: Double?,
    val unit: String?
)

@JsonClass(generateAdapter = true)
data class Recipe(
    val calories: Double,
    val cautions: List<String>,
    val cuisineType: List<String>,
    val dietLabels: List<String>,
    val digest: List<Digest>,
    val dishType: List<String>,
    val healthLabels: List<String>,
    val image: String,
    val images: Images,
    val ingredientLines: List<String>,
    val ingredients: List<Ingredient>,
    val label: String,
    val mealType: List<String>,
    val shareAs: String,
    val source: String,
    val totalDaily: TotalDaily,
    val totalNutrients: TotalNutrients,
    val totalTime: Double,
    val totalWeight: Double,
    val uri: String,
    val url: String,
    val yield: Double
)

@JsonClass(generateAdapter = true)
data class Hit(
    val _links: Links,
    val recipe: Recipe
)


@JsonClass(generateAdapter = true)
data class NutrientInfo(
    val label: String,
    val quantity: Double,
    val unit: String
)

@JsonClass(generateAdapter = true)
data class RecipeSearchResult(
    val _links: LinksX,
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val to: Int
)

@JsonClass(generateAdapter = true)
data class RecipeResult(
    val recipe: Recipe,
    val _links: Links
)
