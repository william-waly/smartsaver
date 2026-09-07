package com.dnb.smartsaver.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dnb.smartsaver.model.SavingsGoal
import com.dnb.smartsaver.model.sampleGoals

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onGoalClick: (SavingsGoal) -> Unit, onAddGoalClick: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Mine sparemål") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddGoalClick) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sampleGoals) { goal ->
                GoalCard(goal = goal, onClick = { onGoalClick(goal) })
            }
        }
    }
}

@Composable
fun GoalCard(goal: SavingsGoal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(goal.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { goal.progress },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "${goal.currentAmount.toInt()} kr av ${goal.targetAmount.toInt()} kr",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}