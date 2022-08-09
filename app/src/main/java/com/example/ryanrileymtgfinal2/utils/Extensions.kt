package com.example.ryanrileymtgfinal2.utils

fun String.httpConvert(): String {
    return replace("http", "https", ignoreCase = false)
}