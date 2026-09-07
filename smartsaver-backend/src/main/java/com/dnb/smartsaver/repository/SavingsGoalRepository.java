package com.dnb.smartsaver.repository;

import com.dnb.smartsaver.model.SavingsGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SavingsGoalRepository extends JpaRepository<SavingsGoal, Long> {
    List<SavingsGoal> findByAccountId(Long accountId);
}