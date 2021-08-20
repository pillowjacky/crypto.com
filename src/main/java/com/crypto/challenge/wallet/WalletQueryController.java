package com.crypto.challenge.wallet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletQueryController {

    @Autowired
    private WalletQueryService walletQueryService;

    @GetMapping("/customer/{id}/wallets")
    public List<Wallet> getCustomerWallets(@PathVariable Long id) {
        return walletQueryService.getCustomerWallets(id);
    }
}
