package com.rlogical.data

import com.rlogical.data.dto.login.User
import com.rlogical.ui.component.login.LoginResponseNew
import kotlinx.coroutines.flow.Flow


interface DataRepositorySource {
    suspend fun doLogin(response: LoginResponseNew): Flow<LoginResponseNew>

}
