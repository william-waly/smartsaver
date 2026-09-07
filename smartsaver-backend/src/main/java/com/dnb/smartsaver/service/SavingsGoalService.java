package com.dnb.smartsaver.service;

import com.dnb.smartsaver.model.SavingsGoal;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class SavingsGoalService {

    /**
     * Regner ut hvor mye brukeren bør spare per uke for å nå målet i tide.
     * Returnerer BigDecimal.ZERO hvis målet allerede er nådd eller fristen er passert.
     */
    public BigDecimal calculateWeeklyContribution(SavingsGoal goal) {
        BigDecimal remaining = goal.getTargetAmount().subtract(goal.getCurrentAmount());

        if (remaining.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        if (goal.getDeadline() == null) {
            throw new IllegalArgumentException("Sparemålet mangler en frist");
        }

        long weeksLeft = ChronoUnit.WEEKS.between(LocalDate.now(), goal.getDeadline());

        if (weeksLeft <= 0) {
            // Fristen er i dag eller passert - alt gjenstående må spares nå
            return remaining;
        }

        return remaining.divide(BigDecimal.valueOf(weeksLeft), 2, RoundingMode.HALF_UP);
    }

    /**
     * Returnerer fremgang mot målet som en prosentandel (0-100).
     */
    public BigDecimal calculateProgressPercentage(SavingsGoal goal) {
        if (goal.getTargetAmount().compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return goal.getCurrentAmount()
                .divide(goal.getTargetAmount(), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(1, RoundingMode.HALF_UP);
    }
}