package com.rlogical.data

import com.rlogical.ui.component.login.LoginResponseNew
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class DataRepository @Inject constructor(

    private val ioDispatcher: CoroutineContext
) :
    DataRepositorySource {


    override suspend fun doLogin(response: LoginResponseNew): Flow<LoginResponseNew> {
        return flow {
            //val res : LoginResponseNew = localRepository.doLogin(response)
            //res.getUser()?.let { insert(it) }
            //response.getUser()?.let { userDao.insert(it) }
            emit(response)
        }.flowOn(ioDispatcher)
    }




}
