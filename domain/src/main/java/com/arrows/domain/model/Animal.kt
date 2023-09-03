package com.arrows.domain.model

data class Animal(
    val age: String,
    val address: Address,
    val colors: Colors,
    val description: String,
    val gender: String,
    val id: Int,
    val name: String,
    val photos: List<Photo>,
    val size: String,
    val type: String,
    val url: String,
)