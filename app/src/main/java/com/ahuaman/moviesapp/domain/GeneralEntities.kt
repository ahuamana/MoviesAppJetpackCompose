package com.ahuaman.moviesapp.domain

class InvalidExceptionGeneral(message: String): Exception(message)

data class GeneralErrorResponse(
    val code: Int,
    val message: String,
    val error: Error
)