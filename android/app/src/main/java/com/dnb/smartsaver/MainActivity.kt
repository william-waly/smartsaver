package com.dnb.smartsaver

import androidx.compose.foundation.layout.fillMaxSize
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.dnb.smartsaver.model.SavingsGoal
import com.dnb.smartsaver.ui.GoalDetailScreen
import com.dnb.smartsaver.ui.HomeScreen
import com.dnb.smartsaver.ui.NewGoalScreen

sealed class Screen {
    object Home : Screen()
    data class Detail(val goal: SavingsGoal) : Screen()
    object NewGoal : Screen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

                    when (val screen = currentScreen) {
                        is Screen.Home -> HomeScreen(
                            onGoalClick = { goal -> currentScreen = Screen.Detail(goal) },
                            onAddGoalClick = { currentScreen = Screen.NewGoal }
                        )
                        is Screen.Detail -> GoalDetailScreen(
                            goal = screen.goal,
                            onBackClick = { currentScreen = Screen.Home }
                        )
                        is Screen.NewGoal -> NewGoalScreen(
                            onBackClick = { currentScreen = Screen.Home },
                            onSaveClick = { _, _, _ -> currentScreen = Screen.Home }
                        )
                    }
                }
            }
        }
    }
}