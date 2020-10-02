package com.dzhafar.lintRules

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class TestRule {
    suspend fun testFun() {
        flowFun().collect {
            print(it)
        }
    }

    fun flowFun(): Flow<Int> = flow { emit(1) }
}