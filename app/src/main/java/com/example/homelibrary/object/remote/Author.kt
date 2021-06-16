package com.example.homelibrary.`object`.remote

data class Author(
    val fullName: String,
    val birthDate: String,
    val books: List<String>     // book titles
) {
    constructor(): this("", "", listOf())
}
