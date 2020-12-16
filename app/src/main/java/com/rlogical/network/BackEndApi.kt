package com.rlogical.network

import com.google.gson.JsonObject
import com.rlogical.ui.component.login.LoginResponseNew
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface BackEndApi {

    @POST("api/login")
    fun login(
        @HeaderMap headers: Map<String, String>,
        @Body body: JsonObject
    ): Call<LoginResponseNew>

}

