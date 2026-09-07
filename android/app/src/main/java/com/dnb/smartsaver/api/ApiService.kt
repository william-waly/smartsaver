package com.dnb.smartsaver.api

import com.dnb.smartsaver.model.SavingsGoal
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("goals/account/{accountId}")
    suspend fun getGoals(@Path("accountId") accountId: Long): List<SavingsGoal>
}