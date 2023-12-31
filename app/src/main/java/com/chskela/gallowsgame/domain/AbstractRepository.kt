package com.chskela.gallowsgame.domain

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

abstract class AbstractRepository {
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }
    protected val coroutineContext by lazy { Dispatchers.IO + coroutineExceptionHandler }
}