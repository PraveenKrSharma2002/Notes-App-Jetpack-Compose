package com.example.notesapp

data class Notes(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val body: String
)