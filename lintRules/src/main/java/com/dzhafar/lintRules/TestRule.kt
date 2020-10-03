package com.dzhafar.lintRules

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class TestRule {
    val args: Flow<Int> = flow { emit(123) }

    suspend fun testFun() {
        args.collect {
            print(it)
        }
    }

    fun flowFun(): Flow<Int> = flow{ emit(111)}
}