package fr.mbds.tdaumas.idbdata.utils

sealed class Result<out T : Any> {
    data class Succes<out T : Any>(val data: T) : Result<T>()
    data class Error(
        val exception: Throwable,
        val code: Int,
        val message: String
    ) : Result<Nothing>()
}