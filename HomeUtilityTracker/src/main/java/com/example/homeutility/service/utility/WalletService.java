package com.example.homeutility.service.utility;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.Wallet;

import java.util.Optional;

public interface WalletService {
    Wallet createWalletForUser(User user);
    Optional<Wallet> getWalletByUser(User user);
    void transferToWallet(User user, double amount);
    void setMonthlyExpenseLimit(User user, double limit);
}
