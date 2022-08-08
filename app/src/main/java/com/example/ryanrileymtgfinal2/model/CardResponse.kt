package com.example.ryanrileymtgfinal2.model

import org.w3c.dom.Node

data class BoosterResponse(
    val data: List<BoosterData>
)

data class BoosterData(
    val node: BoosterNode? = null
)
data class BoosterNode(
    val name: String,
    val code: String
)

data class CardResponse(
    val data: List<CardData>
)

data class CardData(
    val imageUrl: String
)