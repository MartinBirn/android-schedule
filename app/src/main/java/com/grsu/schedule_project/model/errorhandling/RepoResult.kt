package com.grsu.schedule_project.model.errorhandling

import java.io.IOException

sealed class RepoResult<out T, out E> {

    data class Success<T : Any>(val response: T) : RepoResult<T, Nothing>()

    sealed class Failure<out E> : RepoResult<Nothing, E>() {

        public data class NetworkFailure internal constructor(public val error: IOException) :
            Failure<Nothing>()

        public data class UnknownFailure internal constructor(public val error: Throwable) :
            Failure<Nothing>()

        public data class HttpFailure<out E> internal constructor(
            public val code: Int,
            public val error: E?,
        ) : Failure<E>()

        public data class ApiFailure<out E> internal constructor(public val error: E?) :
            Failure<E>()

        data class DatabaseFailure internal constructor(val error: Throwable) : Failure<Nothing>()
    }
}