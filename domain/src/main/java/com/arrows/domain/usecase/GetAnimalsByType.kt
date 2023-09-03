package com.arrows.domain.usecase

import androidx.paging.PagingSource
import com.arrows.domain.model.Animal
import com.arrows.domain.paging.AnimalsByTypePagingSource
import com.arrows.domain.repo.Repo

class GetAnimalsByType( private val repo : Repo) {
    operator fun invoke(type : String) : PagingSource<Int, Animal> = AnimalsByTypePagingSource(repo,type)

}