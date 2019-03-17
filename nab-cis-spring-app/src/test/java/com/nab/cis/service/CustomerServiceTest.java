package com.nab.cis.service;

import com.nab.cis.NabCisSpringAppApplication;
import com.nab.cis.constant.ApplicationConstants;
import com.nab.cis.service.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NabCisSpringAppApplication.class)
@Transactional
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    AddressService addressService;

    private AddressDTO addressDTO, addressDTO1;

    private CustomerDTO customerDTO, customerDTO1;

    private CityDTO cityDTO, cityDTO1;

    private StateDTO stateDTO, statedTO1;

    private CountryDTO countryDTO, countryDTO1;


    @Before
    public void init() {
        countryDTO = new CountryDTO();
        countryDTO.setName("Australia");

        countryDTO1 = new CountryDTO();
        countryDTO1.setName("Australia");

        stateDTO = new StateDTO();
        stateDTO.setName("Victoria");
        stateDTO.setCountry(countryDTO);

        statedTO1 = new StateDTO();
        statedTO1.setName("New SouthWale");
        statedTO1.setCountry(countryDTO1);

        cityDTO = new CityDTO();
        cityDTO.setName("Docklands");
        cityDTO.setState(stateDTO);

        cityDTO1 = new CityDTO();
        cityDTO1.setName("Sydney");
        cityDTO1.setState(statedTO1);

        addressDTO = new AddressDTO();
        addressDTO.setType(ApplicationConstants.ADDRESS_TYPE_MAILING);
        addressDTO.setUnitNumber(200);
        addressDTO.setStreetName("Collins St.");
        addressDTO.setSuburb("Docklands");
        addressDTO.setZipCode(3008);
        addressDTO.setCity(cityDTO);

        addressDTO1 = new AddressDTO();
        addressDTO1.setType(ApplicationConstants.ADDRESS_TYPE_RESIDENTIAL);
        addressDTO1.setUnitNumber(829);
        addressDTO1.setStreetName("Elizabeth St.");
        addressDTO1.setSuburb("Docklands");
        addressDTO1.setZipCode(7654);
        addressDTO1.setCity(cityDTO1);

        customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Sana");
        customerDTO.setMiddleName("Roushana");
        customerDTO.setSurName("Syed");
        customerDTO.setInitial("S");
        customerDTO.setTitle("Miss");
        customerDTO.setSex("Female");
        customerDTO.setMartialStatus("Unmarried");
        customerDTO.setCreditRating(50);
        customerDTO.setNabCustomer(false);


        customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName("Saniya");
        customerDTO1.setMiddleName("Beema");
        customerDTO1.setSurName("Roushana");
        customerDTO1.setInitial("S");
        customerDTO1.setTitle("Miss");
        customerDTO1.setSex("Female");
        customerDTO1.setMartialStatus("Unmarried");
        customerDTO1.setCreditRating(100);
        customerDTO1.setNabCustomer(true);
    }

    @Test
    public void assertThatCustomerCanBeCreated() {
        List<AddressDTO> addressDTOList = new ArrayList<>(1);
        addressDTOList.add(addressDTO);
        customerDTO.setAddresses(addressDTOList);
        CustomerDTO actualCustomer = customerService.createCustomer(customerDTO);

        assertThat(actualCustomer).isNotNull();
        assertThat(actualCustomer.getId()).isNotNull();
        assertThat(actualCustomer.getFirstName()).isEqualTo(customerDTO.getFirstName());
        assertThat(actualCustomer.getMiddleName()).isEqualTo(customerDTO.getMiddleName());
        assertThat(actualCustomer.getSurName()).isEqualTo(customerDTO.getSurName());
        assertThat(actualCustomer.getAddresses()).hasSize(1);
        assertThat(actualCustomer.getAddresses().get(0).getId()).isNotNull();
    }

    @Test
    public void assertThatCustomerCanBeUpdated() {
        List<AddressDTO> addressDTOList = new ArrayList<>(1);
        addressDTOList.add(addressDTO);
        customerDTO.setAddresses(addressDTOList);

        CustomerDTO newCustomer = customerService.createCustomer(customerDTO);
        assertThat(newCustomer).isNotNull();
        assertThat(newCustomer.getId()).isNotNull();

        newCustomer.setFirstName("Saniya");
        newCustomer.setNabCustomer(true);

        Optional<CustomerDTO> actualCustomer = customerService.updateCustomer(newCustomer);

        assertThat(actualCustomer).isNotNull();
        assertThat(actualCustomer.get().getId()).isNotNull();
        assertThat(actualCustomer.get().getFirstName()).isEqualTo(newCustomer.getFirstName());
        assertThat(actualCustomer.get().isNabCustomer()).isEqualTo(newCustomer.isNabCustomer());
    }

    @Test
    public void assertThatCustomerAddressCanBeUpdated() {
        List<AddressDTO> addressDTOList = new ArrayList<>(2);
        addressDTOList.add(addressDTO);
        addressDTOList.add(addressDTO1);
        customerDTO.setAddresses(addressDTOList);

        CustomerDTO newCustomer = customerService.createCustomer(customerDTO);
        assertThat(newCustomer).isNotNull();
        assertThat(newCustomer.getId()).isNotNull();
        assertThat(newCustomer.getAddresses()).isNotNull();

        AddressDTO addressDTO = newCustomer.getAddresses().get(0);
        if(null != addressDTO){
            addressDTO.setUnitNumber(888);
            addressDTO.setSuburb("Tarneit");
            addressDTO.setZipCode(4009);
        }
        Optional<CustomerDTO> actualCustomer = customerService.updateCustomer(newCustomer);
        assertThat(actualCustomer).isNotNull();
        assertThat(actualCustomer.get().getAddresses()).isNotNull();
        assertThat(actualCustomer.get().getAddresses()).hasSize(2);
        assertThat(actualCustomer.get().getAddresses().get(0).getSuburb()).isEqualTo("Tarneit");
        assertThat(actualCustomer.get().getAddresses().get(0).getUnitNumber()).isEqualTo(888);
        assertThat(actualCustomer.get().getAddresses().get(0).getZipCode()).isEqualTo(4009);
    }

    @Test
    public void assertThatCustomerCanBeDeleted() {
        List<AddressDTO> addressDTOList = new ArrayList<>(1);
        addressDTOList.add(addressDTO);
        customerDTO.setAddresses(addressDTOList);

        CustomerDTO newCustomer = customerService.createCustomer(customerDTO);
        assertThat(newCustomer).isNotNull();
        assertThat(newCustomer.getId()).isNotNull();

        customerService.deleteCustomer(newCustomer.getId());
        Optional<CustomerDTO> actualCustomer = customerService.getCustomer(newCustomer.getId());
        assertThat(actualCustomer.isPresent()).isFalse();
    }

    @Test
    public void assertThatAllCustomersCanBeFound() {
        List<AddressDTO> addressDTOList = new ArrayList<>(1);
        addressDTOList.add(addressDTO);
        customerDTO.setAddresses(addressDTOList);

        CustomerDTO newCustomer1 = customerService.createCustomer(customerDTO);
        CustomerDTO newCustomer2 = customerService.createCustomer(customerDTO);

        assertThat(newCustomer1).isNotNull();
        assertThat(newCustomer2).isNotNull();
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        assertThat(allCustomers).hasSize(2);
        CustomerDTO actualCustomer1 = allCustomers.get(0);
        CustomerDTO actualCustomer2 = allCustomers.get(1);
        assertThat(actualCustomer1).isNotNull();
        assertThat(actualCustomer2).isNotNull();
    }
}
