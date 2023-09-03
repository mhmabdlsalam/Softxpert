package com.arrows.domain.usecase

import androidx.paging.PagingSource
import com.arrows.domain.model.Animal
import com.arrows.domain.paging.AnimalsByTypePagingSource
import com.arrows.domain.paging.AnimalsPagingSource
import com.arrows.domain.repo.Repo

class GetAnimals( private val repo :Repo) {
    operator fun invoke() : PagingSource<Int, Animal> = AnimalsPagingSource(repo)

}