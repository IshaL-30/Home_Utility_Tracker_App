package com.example.homeutility.repository.utility;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.homeutility.model.utility.Wallet;
import com.example.homeutility.core.model.User;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
