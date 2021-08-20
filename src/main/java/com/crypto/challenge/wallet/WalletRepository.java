package com.crypto.challenge.wallet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    
    public List<Wallet> findByCustomerId(Long customerId);

    public Wallet findByCustomerIdAndCurrency(Long customerId, String currency);
}
