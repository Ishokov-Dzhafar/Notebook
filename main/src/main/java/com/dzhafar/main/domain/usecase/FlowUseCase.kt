package com.dzhafar.main.domain.usecase

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<T, Params> {
    fun execute(params: Params): Flow<T>
}