package com.dnb.smartsaver.model

data class SavingsGoal(
    val id: Long,
    val name: String,
    val targetAmount: Double,
    val currentAmount: Double,
    val deadline: String
) {
    val progress: Float
        get() = (currentAmount / targetAmount).toFloat().coerceIn(0f, 1f)
}