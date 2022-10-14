package com.example.testapplication.data.modules

import android.content.Context
import androidx.room.Room
import com.example.testapplication.domain.models.local.dao.MealsDao
import com.example.testapplication.domain.models.local.db.TestApplicationDatabase
import com.example.testapplication.domain.models.remote.Api
import com.example.testapplication.domain.repository.*
import com.example.testapplication.domain.usecase.AddMealsUseCase
import com.example.testapplication.domain.usecase.GetFoodLocalUseCase
import com.example.testapplication.domain.usecase.GetFoodRemoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Singleton
    @Provides
    fun provideGetMealsRemoteUseCase(getMealsRemoteManager: GetMealsRemoteManager) =
        GetFoodRemoteUseCase(getMealsRemoteManager)

    @Singleton
    @Provides
    fun provideGetMealsRemoteManager(api: Api): GetMealsRemoteManager = GetMealsRemoteManagerImpl(api)

    @Singleton
    @Provides
    fun provideGetMealsLocalUseCase(getMealsLocalManager: GetMealsLocalManager) =
        GetFoodLocalUseCase(getMealsLocalManager)

    @Singleton
    @Provides
    fun provideGetMealsLocalManager(mealsDao: MealsDao): GetMealsLocalManager =
        GetMealsLocalManagerImpl(mealsDao)

    @Singleton
    @Provides
    fun provideAddMealsUseCase(addMealsRepository: AddMealsRepository) =
        AddMealsUseCase(addMealsRepository)

    @Singleton
    @Provides
    fun provideAddMealsRepository(mealsDao: MealsDao): AddMealsRepository = AddMealsRepositoryImpl(mealsDao)


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TestApplicationDatabase {
        return Room.databaseBuilder(
            appContext,
            TestApplicationDatabase::class.java,
            "TestDatabase"
        ).build()
    }

    @Provides
    fun provideFoodDao(testApplicationDatabase: TestApplicationDatabase): MealsDao {
        return testApplicationDatabase.foodDao()
    }


}