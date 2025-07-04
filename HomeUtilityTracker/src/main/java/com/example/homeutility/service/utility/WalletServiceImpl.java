package com.example.homeutility.service.utility;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.Goal;
import com.example.homeutility.model.utility.Wallet;
import com.example.homeutility.repository.utility.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private GoalService goalService;

    @Override
    public Optional<Wallet> getWalletByUser(User user) {
        return walletRepository.findByUser(user);
    }

    @Override
    public Wallet createWalletForUser(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(0.0);
        wallet.setMonthlyExpenseLimit(0.0);
        return walletRepository.save(wallet);
    }

    @Override
    public void updateMonthlyExpenseLimit(User user, double limit) {
        Wallet wallet = getWalletByUser(user).orElseGet(() -> createWalletForUser(user));
        wallet.setMonthlyExpenseLimit(limit);
        walletRepository.save(wallet);
    }

    @Override
    public void updateBalanceFromGoals(User user) {
        List<Goal> goals = goalService.getGoalsByUser(user);
        double totalSaved = goals.stream()
                .mapToDouble(Goal::getSavedAmount)
                .sum();

        Wallet wallet = getWalletByUser(user).orElseGet(() -> createWalletForUser(user));
        wallet.setBalance(totalSaved);
        walletRepository.save(wallet);
    }
}
