package com.example.testapplication.domain.usecase.base

interface BaseReturnUseCase<T> {
    fun execute(): T
}