package com.arrows.data.repo

import com.arrows.data.remote.ApiService
import com.arrows.domain.model.Animal
import com.arrows.domain.model.AnimalDetailsResponse
import com.arrows.domain.model.TokenResponse
import com.arrows.domain.model.TypeX
import com.arrows.domain.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoImplement(private val api : ApiService):Repo {
    override suspend fun getAnimalsFromServer(page:Int) : List<Animal> = withContext(Dispatchers.IO) {
        val tokenResponse = getAccessToken()
        val header = mapOf(
            "Authorization" to "Bearer ${tokenResponse.access_token}"
        )
        api.getAnimals(header,page).animals


    }

    override suspend fun getAnimalsByType(page: Int, type: String): List<Animal> = withContext(Dispatchers.IO){
        val tokenResponse = getAccessToken()
        val header = mapOf(
            "Authorization" to "Bearer ${tokenResponse.access_token}"
        )
        api.getAnimalsByType(header,page,type).animals
    }

    override suspend fun getAnimalTypesFromServer(): List<TypeX> = withContext(Dispatchers.IO) {
        val tokenResponse = getAccessToken()
        val header = mapOf(
            "Authorization" to "Bearer ${tokenResponse.access_token}"
        )
        api.getAnimalTypes(header).types
    }

    override suspend fun getAnimalDetails(id: Int): AnimalDetailsResponse = withContext(Dispatchers.IO) {
        val tokenResponse = getAccessToken()
        val header = mapOf(
            "Authorization" to "Bearer ${tokenResponse.access_token}"
        )
        api.getAnimalDetails(header,id)
    }


    private suspend fun getAccessToken(): TokenResponse = withContext(Dispatchers.IO){
        api.getAccessToken(headers())
    }


    private fun headers(): Map<String, String> {
        return mapOf(
            "client_id" to "kK4MSKCnl6f8CT0w7qPg9c9VfwfOFPfdQe8CUcPfxWLxMbvQXI",
            "client_secret" to "D31cBJy3f7LMBCU8tgyNMqoNlmmG3JdD1Hhn4ZhA",
            "grant_type" to "client_credentials"
        )
    }



}