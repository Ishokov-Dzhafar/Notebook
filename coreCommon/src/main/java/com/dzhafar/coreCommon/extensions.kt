package com.dzhafar.coreCommon

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@Suppress("TooGenericExceptionCaught")
fun <T> Flow<T>.handleErrors(handleError: (e: Throwable) -> Unit): Flow<T> = flow {
    try {
        collect { value -> emit(value) }
    } catch (error: Throwable) {
        error.printStackTrace()
        handleError(error)
    }
}