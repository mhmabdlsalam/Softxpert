package com.arrows.domain.repo

import com.arrows.domain.model.Animal
import com.arrows.domain.model.AnimalDetailsResponse
import com.arrows.domain.model.TokenResponse
import com.arrows.domain.model.TypeX

interface Repo {

    suspend fun getAnimalsFromServer (page:Int) :List<Animal>
    suspend fun getAnimalsByType (page:Int,type:String) :List<Animal>
    suspend fun getAnimalTypesFromServer () :List<TypeX>
    suspend fun getAnimalDetails (id :Int) :AnimalDetailsResponse



}