package com.dnb.smartsaver.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnb.smartsaver.api.RetrofitClient
import com.dnb.smartsaver.model.SavingsGoal
import kotlinx.coroutines.launch

class GoalsViewModel : ViewModel() {

    var goals by mutableStateOf<List<SavingsGoal>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadGoals(accountId: Long) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                goals = RetrofitClient.apiService.getGoals(accountId)
            } catch (e: Exception) {
                errorMessage = "Kunne ikke hente sparemål: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}