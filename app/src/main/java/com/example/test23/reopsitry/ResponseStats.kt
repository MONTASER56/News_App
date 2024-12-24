package com.example.test23.reopsitry

sealed class ResponseStats<out T> {
    data class Success<T>(val data: T) : ResponseStats<T>()
    data class Error(val message: String) : ResponseStats<Nothing>()
    object loading : ResponseStats<Nothing>()
fun toData():T?= if (this is Success) data else null
}
