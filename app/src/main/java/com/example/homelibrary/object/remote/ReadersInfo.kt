package com.example.homelibrary.`object`.remote

data class ReadersInfo(
    val reader: Reader,
    val startDate: String,
    val endDate: String,
    val actualEndDate: String
)