package com.arrows.data.remote

import com.arrows.domain.model.AnimalDetailsResponse
import com.arrows.domain.model.AnimalRespose
import com.arrows.domain.model.AnimalTypesResponse
import com.arrows.domain.model.TokenResponse
import retrofit2.http.*

interface ApiService  {

    @POST("oauth2/token")
    suspend fun getAccessToken(@Body headers : Map<String,String>) : TokenResponse

    @GET("animals")
    suspend fun getAnimals(@HeaderMap map: Map<String,String>,@Query("page")  page : Int ): AnimalRespose

    @GET("types")
    suspend fun getAnimalTypes(@HeaderMap map: Map<String,String>): AnimalTypesResponse

    @GET("animals")
    suspend fun getAnimalsByType(@HeaderMap map: Map<String,String>,@Query("page")  page : Int
                           ,@Query("type") type :String): AnimalRespose

    @GET("animals/{id}")
    suspend fun getAnimalDetails(@HeaderMap map: Map<String,String>,@Path("id") id:Int ):AnimalDetailsResponse


}