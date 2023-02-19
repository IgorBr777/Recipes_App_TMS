package com.example.recipes.data.model

data class RecipesResponse(
    val offset: Int,
    val number: Int,
    val results: List<Result>,
    val totalResults: Int
) {
    data class Result(
        val id: Int,
        val image: String,
        val imageType: String,
        val title: String
    )
}
