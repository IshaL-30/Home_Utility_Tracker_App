package com.example.homeutility.service.utility;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.Wallet;

import java.util.Optional;

public interface WalletService {
	Optional<Wallet> getWalletByUser(User user);
    Wallet createWalletForUser(User user);
    void updateMonthlyExpenseLimit(User user, double limit);
    void updateBalanceFromGoals(User user);
}
