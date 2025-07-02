package com.example.homeutility.service.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.Wallet;
import com.example.homeutility.repository.utility.WalletRepository;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

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
    @Transactional
    public void transferToWallet(User user, double amount) {
        Wallet wallet = walletRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

    @Override
    public void setMonthlyExpenseLimit(User user, double limit) {
        Wallet wallet = walletRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.setMonthlyExpenseLimit(limit);
        walletRepository.save(wallet);
    }
}
