package com.arrows.domain.usecase

import com.arrows.domain.repo.Repo

class GetAnimalTypes ( private val repo : Repo) {
    suspend operator fun invoke() :List<String> {
        val list = repo.getAnimalTypesFromServer().map {
            it.name
        }.toMutableList()
        list.add(0,"All")
        return list.toList()
    }
}