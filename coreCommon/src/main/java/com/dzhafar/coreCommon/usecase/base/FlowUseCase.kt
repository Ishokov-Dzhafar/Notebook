package com.dzhafar.coreCommon.usecase.base

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<T, Params> {
    fun execute(params: Params): Flow<T>
}