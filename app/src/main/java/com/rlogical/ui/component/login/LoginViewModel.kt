package com.rlogical.ui.component.login

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.rlogical.data.DataRepository
import com.rlogical.data.Resource
import com.rlogical.data.error.PASS_WORD_ERROR
import com.rlogical.data.error.USER_NAME_ERROR
import com.rlogical.network.BackEndApi
import com.rlogical.network.WebServiceClient
import com.rlogical.ui.base.BaseViewModel

import com.rlogical.utils.SingleEvent
import com.rlogical.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject



class LoginViewModel @Inject constructor(private val dataRepository: DataRepository) : BaseViewModel() ,
    Callback<LoginResponseNew> {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val loginLiveDataPrivate = MutableLiveData<Resource<LoginResponseNew>>()
    val loginLiveData: LiveData<Resource<LoginResponseNew>> get() = loginLiveDataPrivate

    /** Error handling as UI **/

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun doLogin(userName: String, passWord: String) =
        if(userName.isEmpty() || userName.trim().length > 30){
            loginLiveDataPrivate.value = Resource.DataError(USER_NAME_ERROR)
        } else if(passWord.isEmpty() || passWord.trim().length > 16){
            loginLiveDataPrivate.value = Resource.DataError(PASS_WORD_ERROR)
        } else {

            val header: LinkedHashMap<String, String> = LinkedHashMap()
            header["IMEI"] = "510110406068589"
            header["IMSI"] = "357175048449937"

            val jObj = JSONObject()
            jObj.put("username", userName)
            jObj.put("password", passWord)

            val cjObj = Gson().fromJson(
                jObj.toString(),
                JsonObject::class.java
            )

            WebServiceClient.client.create(BackEndApi::class.java).login(header, cjObj).enqueue(this)

        }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }

    override fun onResponse(call: Call<LoginResponseNew>, response: Response<LoginResponseNew>) {
        viewModelScope.launch {
            //loginLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                response.body()?.let {
                    dataRepository.doLogin(it).collect {

                        loginLiveDataPrivate.value = Resource.Loading(response.body())
                        Log.w("A","AAA");


                    }
                }
            }
        }


    }

    override fun onFailure(call: Call<LoginResponseNew>, t: Throwable) {

    }

    /*@WorkerThread
    suspend fun insert(user: User) = withContext(Dispatchers.IO) {
        dataRepository.insert(user)
    }*/

}
