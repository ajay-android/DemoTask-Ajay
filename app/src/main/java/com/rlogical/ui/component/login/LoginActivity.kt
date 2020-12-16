package com.rlogical.ui.component.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.rlogical.data.Resource
import com.rlogical.data.dto.login.User
import com.rlogical.data.dto.login.UserRoomDatabase
import com.rlogical.databinding.LoginActivityBinding
import com.rlogical.ui.ViewModelFactory
import com.rlogical.ui.base.BaseActivity
import com.rlogical.utils.*
import javax.inject.Inject


class LoginActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: LoginActivityBinding
    var db: UserRoomDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val database = UserRoomDatabase.getDatabase()

        db = Room.databaseBuilder(
            applicationContext,
            UserRoomDatabase::class.java, "demo-database"
        ).build()

        binding.login.setOnClickListener { doLogin() }
    }

    override fun initViewBinding() {
        binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun initializeViewModel() {
        loginViewModel = viewModelFactory.create(loginViewModel::class.java)
    }

    override fun observeViewModel() {
        observe(loginViewModel.loginLiveData, ::handleLoginResult)
        observeSnackBarMessages(loginViewModel.showSnackBar)
        observeToast(loginViewModel.showToast)
    }

    private fun doLogin() {
        binding.loaderView.toVisible()
        loginViewModel.doLogin(
            binding.username.text.trim().toString(),
            binding.password.text.toString()
        )
    }

    private fun handleLoginResult(response: Resource<LoginResponseNew>) {
        binding.loaderView.toGone()

        if (response.data != null) {

            if (response.data.getErrorCode() == "00") {

                //Insert Case
                val thread = Thread {
                    var bookEntity = User()
                    bookEntity.userId = response.data.getUser()!!.userId
                    bookEntity.userName = response.data.getUser()!!.userName

                    if(!db!!.userDao().isExitsUser(bookEntity.userId)){
                        db!!.userDao().insertUser(bookEntity)
                    }

                    //fetch Records
                    db!!.userDao().getAllUser().forEach()
                    {
                        Log.i("Fetch Records", "Id:  : ${it.userId}")
                        Log.i("Fetch Records", "Name:  : ${it.userName}")
                    }
                }
                thread.start()
                Toast.makeText(
                    this,
                    "Welcome, ${response.data?.getUser()?.userName}",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(this, "${response.data?.getErrorMessage()}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }



}
