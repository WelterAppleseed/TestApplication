package com.example.testapplication.domain.usecase.base

interface SuspendReturnUseCase<T> {
    suspend fun execute(): T

}