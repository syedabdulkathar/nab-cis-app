package com.nab.cis.service;

import com.nab.cis.domain.City;
import com.nab.cis.domain.State;
import com.nab.cis.service.dto.AddressDTO;
import com.nab.cis.service.dto.CityDTO;
import com.nab.cis.service.dto.CountryDTO;
import com.nab.cis.service.dto.StateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<CountryDTO> getAllCountries();
    Optional<CountryDTO> getByCountry(String name);
    Optional<CityDTO> getByCity(String name);
    Optional<StateDTO> getByState(String name);

}
