package com.nab.cis.service;

import com.nab.cis.service.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    Optional<CustomerDTO> getCustomer(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    Optional<CustomerDTO> updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long id);
}

