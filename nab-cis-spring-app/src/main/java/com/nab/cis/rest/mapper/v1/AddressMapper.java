package com.nab.cis.rest.mapper.v1;

import com.nab.cis.domain.Address;
import com.nab.cis.domain.City;
import com.nab.cis.domain.Country;
import com.nab.cis.domain.State;
import com.nab.cis.service.dto.AddressDTO;
import com.nab.cis.service.dto.CityDTO;
import com.nab.cis.service.dto.CountryDTO;
import com.nab.cis.service.dto.StateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO addressToAddressDTO(Address address);

    CountryDTO countryToCountryDTO(Country country);

    StateDTO stateToStateDTO(State state);

    CityDTO cityToCityDTO(City city);

}
