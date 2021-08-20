package com.crypto.challenge.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEventRepository extends JpaRepository<TransactionEvent, Long> {

}
