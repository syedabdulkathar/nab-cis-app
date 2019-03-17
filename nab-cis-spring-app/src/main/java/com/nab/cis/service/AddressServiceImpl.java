package com.nab.cis.service;

import com.nab.cis.domain.Address;
import com.nab.cis.domain.City;
import com.nab.cis.domain.State;
import com.nab.cis.repository.AddressRepository;
import com.nab.cis.repository.CityRepository;
import com.nab.cis.repository.CountryRepository;
import com.nab.cis.repository.StateRepository;
import com.nab.cis.rest.mapper.v1.AddressMapper;
import com.nab.cis.service.dto.AddressDTO;
import com.nab.cis.service.dto.CityDTO;
import com.nab.cis.service.dto.CountryDTO;
import com.nab.cis.service.dto.StateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    private Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
    private AddressRepository addressRepository;

    private CountryRepository countryRepository;

    private StateRepository stateRepository;

    private CityRepository cityRepository;

    private AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository,
                              CountryRepository countryRepository,
                              StateRepository stateRepository,
                              CityRepository cityRepository,
                              AddressMapper addressMapper) {
        super();
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(country -> {
                    return addressMapper.countryToCountryDTO(country);
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<CountryDTO> getByCountry(String name) {
        return Optional.of(countryRepository.findByNameIgnoreCase(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(country -> {
                            return addressMapper.countryToCountryDTO(country);
                        }
                );
    }

    @Override
    public Optional<CityDTO> getByCity(String name) {
        return Optional.of(cityRepository.findByNameIgnoreCase(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(city -> {
                    return addressMapper.cityToCityDTO(city);
                });
    }

    @Override
    public Optional<StateDTO> getByState(String name) {
        return Optional.of(stateRepository.findByNameIgnoreCase(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(state -> {
                    return addressMapper.stateToStateDTO(state);
                });
    }

//    @Override
//    public List<StateDTO> getStatesByCountryId(Long countryId) {
//        return Optional.of(countryRepository.findById(countryId))
//                .filter(Optional::isPresent)
//                .map(country ->
//                        {
//                            return addressMapper.countryToCountryDTO(country.get()).getStates();
//                        }
//                ).get();
//    }

//    @Override
//    public List<CityDTO> getCitiesByStateId(Long stateId) {
//        logger.info("getCitiesByStateId>>>>" + stateId);
//        return Optional.of(stateRepository.findById(stateId))
//                .filter(Optional::isPresent)
//                .map(state ->
//                        {
//                            return addressMapper.stateToStateDTO(state.get()).getCities();
//                        }
//                ).get();
//    }
}
