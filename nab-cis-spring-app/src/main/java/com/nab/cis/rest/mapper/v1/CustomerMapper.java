package com.nab.cis.rest.mapper.v1;

import com.nab.cis.domain.Customer;
import com.nab.cis.service.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customertoCustomerDTO(Customer customer);

    Customer customerDTOtoCustomer(CustomerDTO customerDTO);
}
