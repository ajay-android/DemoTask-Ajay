package com.rlogical.usecase.errors

import com.rlogical.data.error.Error

interface ErrorFactory {
    fun getError(errorCode: Int): Error
}
