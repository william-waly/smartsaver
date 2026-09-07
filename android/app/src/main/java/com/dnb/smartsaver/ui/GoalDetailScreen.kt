package com.dnb.smartsaver.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dnb.smartsaver.model.SavingsGoal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalDetailScreen(goal: SavingsGoal, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(goal.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Text("←")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Text("Fremgang", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { goal.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("${(goal.progress * 100).toInt()}% oppnådd")

            Spacer(modifier = Modifier.height(32.dp))
            Text("Detaljer", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Målbeløp: ${goal.targetAmount.toInt()} kr")
            Text("Oppspart: ${goal.currentAmount.toInt()} kr")
            Text("Frist: ${goal.deadline}")
        }
    }
}