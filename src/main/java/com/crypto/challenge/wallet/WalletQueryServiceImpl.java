package com.crypto.challenge.wallet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletQueryServiceImpl implements WalletQueryService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> getCustomerWallets(Long customerId) {
        return walletRepository.findByCustomerId(customerId);
    }
}
