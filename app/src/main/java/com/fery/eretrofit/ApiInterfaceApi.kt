package com.fery.eretrofit

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterfaceApi {
    @GET("/api/users?page=2")
    suspend fun getAllUsers(): Response<ResponselList>
}