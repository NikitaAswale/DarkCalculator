package com.example.scientificcalculator.di

import com.example.scientificcalculator.domain.CalculatorEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCalculatorEngine(): CalculatorEngine = CalculatorEngine()
}
