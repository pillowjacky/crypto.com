package com.crypto.challenge.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletEventRepository extends JpaRepository<WalletEvent, Long> {

}
