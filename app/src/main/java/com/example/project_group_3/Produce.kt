package com.example.project_group_3

enum class ProduceType{
    VEGETABLE,FRUIT
}

data class Produce (
    val id:Int,
    val name:String,
    val type:ProduceType,
    val pricePerPound:Float,
    val description:String,
    val quantityPerPound:Float,
    val image:String,
    )