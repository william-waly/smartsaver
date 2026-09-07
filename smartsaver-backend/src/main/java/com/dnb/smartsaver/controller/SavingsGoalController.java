package com.dnb.smartsaver.controller;

import com.dnb.smartsaver.model.SavingsGoal;
import com.dnb.smartsaver.repository.SavingsGoalRepository;
import com.dnb.smartsaver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/goals")
public class SavingsGoalController {

    @Autowired
    private SavingsGoalRepository goalRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public SavingsGoal createGoal(@RequestBody SavingsGoal goal, @RequestParam Long accountId) {
        goal.setAccount(accountRepository.findById(accountId).orElseThrow());
        return goalRepository.save(goal);
    }

    @GetMapping("/account/{accountId}")
    public List<SavingsGoal> getGoalsForAccount(@PathVariable Long accountId) {
        return goalRepository.findByAccountId(accountId);
    }

    @PutMapping("/{id}/contribute")
    public SavingsGoal contribute(@PathVariable Long id, @RequestParam java.math.BigDecimal amount) {
        SavingsGoal goal = goalRepository.findById(id).orElseThrow();
        goal.setCurrentAmount(goal.getCurrentAmount().add(amount));
        return goalRepository.save(goal);
    }

    @Autowired
    private com.dnb.smartsaver.service.SavingsGoalService savingsGoalService;

    @GetMapping("/{id}/recommendation")
    public java.util.Map<String, Object> getRecommendation(@PathVariable Long id) {
        SavingsGoal goal = goalRepository.findById(id).orElseThrow();
        return java.util.Map.of(
                "weeklyContribution", savingsGoalService.calculateWeeklyContribution(goal),
                "progressPercentage", savingsGoalService.calculateProgressPercentage(goal)
        );
    }
}