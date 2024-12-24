package com.example.test23.mapper

interface Mapper<I,O> {

    fun map(input:I):O
}