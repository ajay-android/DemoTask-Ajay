package com.rlogical.di

import com.rlogical.ui.component.login.LoginActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {

    @ContributesAndroidInjector()
    abstract fun contributeLoginActivity(): LoginActivity
}
