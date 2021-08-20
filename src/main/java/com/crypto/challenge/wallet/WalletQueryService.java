package com.crypto.challenge.wallet;

import java.util.List;

public interface WalletQueryService {

    public List<Wallet> getCustomerWallets(Long customerId);
}
