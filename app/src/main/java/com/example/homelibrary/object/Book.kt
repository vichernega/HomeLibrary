package com.example.homelibrary.`object`

data class Book (
    val title: String,
    val authors: List<String>,      // authors full names
    val price: String,
    val publisher: String,
    val publishingDate: String,
    val comment: String,
    val shelfNumber: String,
    val readingsAmount: String
) {
    constructor() : this("", mutableListOf(), "", "", "", "", "", "")
}