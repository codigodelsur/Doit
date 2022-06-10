package com.codigodelsur.doit.data

import android.content.Context
import com.codigodelsur.doit.data.framework.db.AppDatabase
import com.codigodelsur.doit.data.framework.db.dao.TaskDao
import com.codigodelsur.doit.data.source.TaskLocalDataSource
import com.codigodelsur.doit.data.source.TaskLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractDataModule {

    @Binds
    @Singleton
    abstract fun bindTaskLocalDataSource(
        taskLocalDataSourceImpl: TaskLocalDataSourceImpl
    ): TaskLocalDataSource

}

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.buildDatabase(context = appContext)
    }

    @Provides
    @Singleton
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

}
