package com.crypto.challenge.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {

    @Autowired
    private CustomerQueryRepository customerQueryRepository;

    @Override
    public Customer getCustomer(Long id) {
        return customerQueryRepository.findById(id).get();
    }
}
