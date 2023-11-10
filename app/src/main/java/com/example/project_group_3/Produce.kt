package com.example.project_group_3

data class Produce(
    val id: Int = 0,
    val name: String = "",
    val type: String = "",
    val pricePerPound: Double = 0.0,
    val description: String = "",
    var quantityPerPound: Double = 0.0,
    val image: String = ""
):java.io.Serializable