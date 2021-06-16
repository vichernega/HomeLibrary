package com.example.homelibrary.`object`.remote

data class Reader(
    val fullName: String,
    val address: String,
    val contacts: List<String>,
    val books: Map<String, String>    // Map<bookTitle, readingsNumber>
) {
    constructor(): this("", "", listOf(), mapOf())
}
