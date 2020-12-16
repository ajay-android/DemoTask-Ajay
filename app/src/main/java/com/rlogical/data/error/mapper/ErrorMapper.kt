package com.rlogical.data.error.mapper

import com.rlogical.App
import com.rlogical.R
import com.rlogical.data.error.*
import javax.inject.Inject

class ErrorMapper @Inject constructor() : ErrorMapperInterface {

    override fun getErrorString(errorId: Int): String {
        return App.context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
                Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
                Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),
                Pair(PASS_WORD_ERROR, getErrorString(R.string.invalid_password)),
                Pair(USER_NAME_ERROR, getErrorString(R.string.invalid_username)),
                Pair(CHECK_YOUR_FIELDS, getErrorString(R.string.invalid_username_and_password)),
        ).withDefault { getErrorString(R.string.network_error) }
}
