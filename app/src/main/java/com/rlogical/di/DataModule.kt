
package com.rlogical.di

import com.rlogical.data.DataRepository
import com.rlogical.data.DataRepositorySource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

// Tells Dagger this is a Dagger module
@Module
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): DataRepositorySource
}
