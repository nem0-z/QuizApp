package com.example.quizapp

data class Question (
    val id:Int,
    val image:Int,
    val op1: String,
    val op2: String,
    val op3: String,
    val op4: String,
    val correct: Int,
    val question:String = "What country does this flag belong to?"
)
