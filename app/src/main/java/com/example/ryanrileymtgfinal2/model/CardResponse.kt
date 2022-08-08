package com.example.ryanrileymtgfinal2.model

import org.w3c.dom.Node

data class BoosterResponse(
    val sets: List<BoosterNode>
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