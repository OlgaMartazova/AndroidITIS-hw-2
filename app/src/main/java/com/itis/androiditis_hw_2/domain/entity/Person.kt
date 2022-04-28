package com.itis.androiditis_hw_2.domain.entity

data class Person(
    val id: Int,
    val name: String,
    val nickname: String,
    val img: String,
    val occupation: List<String>,
    val status: String,
    val appearance: List<Int>,
    val portrayed: String,
)
