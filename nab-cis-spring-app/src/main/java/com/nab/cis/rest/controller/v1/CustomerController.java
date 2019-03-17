package com.nab.cis.rest.controller.v1;

import com.nab.cis.service.CustomerService;
import com.nab.cis.service.ValidationErrorService;
import com.nab.cis.service.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ValidationErrorService validationErrorService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // GET: all Customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }
    // POST: create new customer
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDTO customerDTO, BindingResult result) {
        logger.debug("REST API request for create new customer : {}", customerDTO);
        ResponseEntity<?> fieldErrorsMap = validationErrorService.mapValidationService(result);
        if (null != fieldErrorsMap)
            return fieldErrorsMap;
        CustomerDTO newCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    // PUT: update customer
    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerDTO customerDTO, BindingResult result) {
        logger.debug("REST API request for update existing customer : {}", customerDTO);
        ResponseEntity<?> fieldErrorsMap = validationErrorService.mapValidationService(result);
        if (null != fieldErrorsMap)
            return fieldErrorsMap;
        Optional<CustomerDTO> newCustomer = customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
    // DELETE: delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(required = true) Long id) {
        logger.debug("REST request to delete Customer : {}", id);
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

}
