package com.nab.cis.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.cis.constant.ApplicationConstants;
import com.nab.cis.service.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerServiceIntTest {

    @MockBean
    private CustomerService mockCustomerService;

    @Autowired
    private MockMvc mockMvc;

    private CustomerDTO mockCustomerDTO;

    private CustomerDTO mockCustomerDTO() {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName("Australia");

        StateDTO stateDTO = new StateDTO();
        stateDTO.setName("Victoria");
        stateDTO.setCountry(countryDTO);

        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Docklands");
        cityDTO.setState(stateDTO);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setType(ApplicationConstants.ADDRESS_TYPE_MAILING);
        addressDTO.setUnitNumber(200);
        addressDTO.setStreetName("Collins St.");
        addressDTO.setSuburb("Docklands");
        addressDTO.setZipCode(3008);
        addressDTO.setCity(cityDTO);

        mockCustomerDTO = new CustomerDTO();
        mockCustomerDTO.setFirstName("Syed");
        mockCustomerDTO.setMiddleName("Kathar");
        mockCustomerDTO.setSurName("Shahul");
        mockCustomerDTO.setInitial("S");
        mockCustomerDTO.setTitle("Mr");
        mockCustomerDTO.setSex("Male");
        mockCustomerDTO.setMartialStatus("UnMarried");
        mockCustomerDTO.setCreditRating(2);
        mockCustomerDTO.setNabCustomer(false);
        List<AddressDTO> addressDTOList = new ArrayList<>(1);
        addressDTOList.add(addressDTO);
        mockCustomerDTO.setAddresses(addressDTOList);
        return mockCustomerDTO;
    }



    @Test
    @DisplayName("POST /Customer - success")
    void testCreateCustomer() throws Exception {
        // setup Mock Service
        CustomerDTO postCustomerDTO = mockCustomerDTO();
        CustomerDTO mockCustomerDTO = mockCustomerDTO();
        mockCustomerDTO.setId(1L);
        doReturn(mockCustomerDTO).when(mockCustomerService).createCustomer(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(postCustomerDTO)))

                // validate the response and content type
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))

        //validate the return fields
        .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Syed")))
                        .andExpect(jsonPath("$.creditRating", is(2)));
    }

    @Test
    @DisplayName("PUT /Customer - success")
    void testUpdateCustomer() throws Exception {
        // setup Mocked service
        CustomerDTO putCustomerDTO = mockCustomerDTO();
        CustomerDTO mockCustomerDTO = mockCustomerDTO();
        mockCustomerDTO.setId(1L);
        mockCustomerDTO.setFirstName("Sana");
        mockCustomerDTO.setCreditRating(3);
        doReturn(Optional.of(mockCustomerDTO)).when(mockCustomerService).updateCustomer(any());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(putCustomerDTO)))

                // validate the response code and return type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))

                //validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Sana")))
                .andExpect(jsonPath("$.creditRating", is(3)));
    }

    @Test
    @DisplayName("GET /Customers - Success")
    void testGetAllCustomers() throws Exception {
        // setup Mocked service
        CustomerDTO mockCustomerDTO1 = mockCustomerDTO();
        mockCustomerDTO1.setId(1L);

        List mockCustomerResultLst = new ArrayList(2);
        mockCustomerResultLst.add(mockCustomerDTO1);
       ;
        doReturn(mockCustomerResultLst).when(mockCustomerService).getAllCustomers();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))

                //validate the returned fields
                .andExpect(jsonPath("$.[*].title").value(hasItem("Mr")))
                .andExpect(jsonPath("$.[*].firstName").value(hasItem("Syed")))
                .andExpect(jsonPath("$.[*].surName").value(hasItem("Shahul")))
                .andExpect(jsonPath("$.[*].creditRating").value(hasItem(2)));
    }

    @Test
    @DisplayName("DELETE /Customer/1 - Success")
    void testDeleteCustomer() throws Exception {
        CustomerDTO mockCustomerDTO1 = mockCustomerDTO();
        mockCustomerDTO1.setId(1L);
        doReturn(Optional.of(mockCustomerDTO1)).when(mockCustomerService).getCustomer(1L);
        doNothing().when(mockCustomerService).deleteCustomer((long) 1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/{id}", 1))
                .andExpect(status().isOk());
    }

    static String asJsonString(final Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
