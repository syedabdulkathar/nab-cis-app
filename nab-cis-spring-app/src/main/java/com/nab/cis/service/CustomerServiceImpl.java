package com.nab.cis.service;

import com.nab.cis.domain.*;
import com.nab.cis.repository.AddressRepository;
import com.nab.cis.repository.CityRepository;
import com.nab.cis.repository.CustomerRepository;
import com.nab.cis.rest.exception.CustomerIdAlreadyExistsException;
import com.nab.cis.rest.exception.CustomerNotFoundException;
import com.nab.cis.rest.mapper.v1.CustomerMapper;
import com.nab.cis.service.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    return customerMapper.customertoCustomerDTO(customer);
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomer(Long id) {
        return Optional.of(customerRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(customer -> {
                    return customerMapper.customertoCustomerDTO(customer);
                });
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer newCustomer = null;
        if (null != customerDTO.getId()) {
            throw new CustomerIdAlreadyExistsException("Customer already exists: customer Id: " + customerDTO.getId());
        } else {
            Customer customerObj = customerMapper.customerDTOtoCustomer(customerDTO);
            logger.info("customerObj::::"+customerObj.toString());
            newCustomer = customerRepository.save(customerObj);
        }
        logger.info("created new customer, "+newCustomer);
        return customerMapper.customertoCustomerDTO(newCustomer);
    }

    @Override
    public Optional<CustomerDTO> updateCustomer(CustomerDTO customerDTO) {
        return Optional.of(customerRepository.findById(customerDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(customer -> {
                    customer = customerMapper.customerDTOtoCustomer(customerDTO);
                    customerRepository.save(customer);
                    return customer;
                })
                .map(customer -> {
                    return customerMapper.customertoCustomerDTO(customer);
                });
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            customerRepository.delete(customer.get());
        }
        else {
            throw new CustomerNotFoundException("Requested customer not exists to delete. " + id);
        }

    }
}
