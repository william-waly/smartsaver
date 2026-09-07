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

// Midlertidig testdata frem til vi kobler på ekte API-kall
val sampleGoals = listOf(
    SavingsGoal(1, "Ferie til Italia", 15000.0, 4200.0, "2027-06-01"),
    SavingsGoal(2, "Ny laptop", 12000.0, 9800.0, "2026-12-15"),
    SavingsGoal(3, "Buffer", 30000.0, 30000.0, "2027-01-01")
)