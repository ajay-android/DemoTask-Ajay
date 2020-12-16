package com.rlogical.usecase.errors

import com.rlogical.data.error.Error
import com.rlogical.data.error.mapper.ErrorMapper
import javax.inject.Inject



class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorFactory {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
