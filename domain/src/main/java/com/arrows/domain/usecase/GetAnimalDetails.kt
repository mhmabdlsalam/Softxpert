package com.arrows.domain.usecase

import androidx.paging.PagingSource
import com.arrows.domain.model.Animal
import com.arrows.domain.model.AnimalDetailsResponse
import com.arrows.domain.paging.AnimalsPagingSource
import com.arrows.domain.repo.Repo

class GetAnimalDetails ( private val repo : Repo) {
    suspend operator fun invoke(id:Int) = repo.getAnimalDetails(id)

}