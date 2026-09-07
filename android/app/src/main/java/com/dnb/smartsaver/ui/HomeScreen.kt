package com.dnb.smartsaver.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dnb.smartsaver.model.SavingsGoal
import com.dnb.smartsaver.viewmodel.GoalsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onGoalClick: (SavingsGoal) -> Unit,
    onAddGoalClick: () -> Unit,
    viewModel: GoalsViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadGoals(accountId = 1) // midlertidig hardkodet til konto 1
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mine sparemål") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddGoalClick) {
                Text("+")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                viewModel.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                viewModel.errorMessage != null -> {
                    Text(
                        viewModel.errorMessage ?: "",
                        modifier = Modifier.align(Alignment.Center).padding(16.dp)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(viewModel.goals) { goal ->
                            GoalCard(goal = goal, onClick = { onGoalClick(goal) })
                        }
                    }
                }
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