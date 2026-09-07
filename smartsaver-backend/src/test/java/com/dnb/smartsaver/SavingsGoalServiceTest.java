package com.dnb.smartsaver.service;

import com.dnb.smartsaver.model.SavingsGoal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SavingsGoalServiceTest {

    private final SavingsGoalService service = new SavingsGoalService();

    @Test
    void calculatesWeeklyContributionCorrectly() {
        SavingsGoal goal = new SavingsGoal();
        goal.setTargetAmount(new BigDecimal("1000"));
        goal.setCurrentAmount(new BigDecimal("0"));
        goal.setDeadline(LocalDate.now().plusWeeks(10));

        BigDecimal result = service.calculateWeeklyContribution(goal);

        assertEquals(new BigDecimal("100.00"), result);
    }

    @Test
    void returnsZeroWhenGoalAlreadyReached() {
        SavingsGoal goal = new SavingsGoal();
        goal.setTargetAmount(new BigDecimal("1000"));
        goal.setCurrentAmount(new BigDecimal("1000"));
        goal.setDeadline(LocalDate.now().plusWeeks(5));

        assertEquals(BigDecimal.ZERO, service.calculateWeeklyContribution(goal));
    }

    @Test
    void calculatesProgressPercentageCorrectly() {
        SavingsGoal goal = new SavingsGoal();
        goal.setTargetAmount(new BigDecimal("1000"));
        goal.setCurrentAmount(new BigDecimal("250"));

        assertEquals(new BigDecimal("25.0"), service.calculateProgressPercentage(goal));
    }
}