package com.arrows.softxperttask.di

import com.arrows.domain.repo.Repo
import com.arrows.domain.usecase.GetAnimalDetails
import com.arrows.domain.usecase.GetAnimalTypes
import com.arrows.domain.usecase.GetAnimals
import com.arrows.domain.usecase.GetAnimalsByType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase (repo: Repo) :GetAnimals{
        return GetAnimals(repo)
    }

    @Provides
    fun provideTypesUseCase (repo: Repo) :GetAnimalTypes{
        return GetAnimalTypes(repo)
    }

    @Provides
    fun provideAnimalsByTypeUseCase (repo: Repo) :GetAnimalsByType{
        return GetAnimalsByType(repo)
    }

    @Provides
    fun provideAnimalDetailsUseCase (repo: Repo) :GetAnimalDetails{
        return GetAnimalDetails(repo)
    }

}