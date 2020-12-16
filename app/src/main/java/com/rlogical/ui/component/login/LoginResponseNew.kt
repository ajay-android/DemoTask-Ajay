package com.rlogical.ui.component.login

import com.rlogical.data.dto.login.User

class LoginResponseNew {

    private var errorMessage: String? = null

    private var errorCode: String? = null

    private var user: User? = null



    fun getErrorMessage(): String? {
        return errorMessage
    }

    fun setErrorMessage(errorMessage: String?) {
        this.errorMessage = errorMessage
    }

    fun getErrorCode(): String? {
        return errorCode
    }

    fun setErrorCode(errorCode: String?) {
        this.errorCode = errorCode
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User?) {
        this.user = user
    }

    override fun toString(): String {
        return "ClassPojo [errorMessage = $errorMessage, errorCode = $errorCode, user = $user]"
    }

}