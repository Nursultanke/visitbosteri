package com.example.visitbosteri.services

import com.example.visitbosteri.models.Hotels
import retrofit2.Response
import retrofit2.http.GET

interface ReqService {
    @GET("/api/listings/")
    suspend fun getPost() : Response<Hotels>
}