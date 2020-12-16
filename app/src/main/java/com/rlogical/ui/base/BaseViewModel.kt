package com.rlogical.ui.base

import androidx.lifecycle.ViewModel
import com.rlogical.data.error.mapper.ErrorMapper
import com.rlogical.usecase.errors.ErrorManager





abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    val errorManager: ErrorManager = ErrorManager(ErrorMapper())
}
