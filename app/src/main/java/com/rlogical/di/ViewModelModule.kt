package com.rlogical.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rlogical.ui.ViewModelFactory
import com.rlogical.ui.component.login.LoginViewModel


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}
