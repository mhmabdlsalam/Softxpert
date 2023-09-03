package com.arrows.softxperttask.di

import com.arrows.data.remote.ApiService
import com.arrows.data.repo.RepoImplement
import com.arrows.domain.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo (apiService: ApiService) :Repo{
        return RepoImplement(apiService)
    }

}