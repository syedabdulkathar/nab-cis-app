package com.nab.cis.service;

import static org.assertj.core.api.Assertions.assertThat;
import com.nab.cis.NabCisSpringAppApplication;
import com.nab.cis.service.dto.CityDTO;
import com.nab.cis.service.dto.CountryDTO;
import com.nab.cis.service.dto.StateDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NabCisSpringAppApplication.class)
@Transactional
public class AddressServiceTest {
    @Autowired
    AddressService addressService;

    @Test
    @Transactional
    public void assertThatAllCountriesCanBeFound() {
        List<CountryDTO> countries = addressService.getAllCountries();
        assertThat(countries).isNotNull();
        assertThat(countries).hasSize(2);
    }

    @Test
    @Transactional
    public void assertThatCountryCanBeFound() {
        Optional<CountryDTO> country = addressService.getByCountry("Australia");
        assertThat(country).isNotNull();
        assertThat(country.get().getName()).isEqualTo("Australia");
    }

    @Test
    @Transactional
    public void assertThatStateCanBeFound() {
        Optional<StateDTO> state = addressService.getByState("NSW");
        assertThat(state).isNotNull();
        assertThat(state.get().getName()).isEqualTo("NSW");
    }

    @Test
    @Transactional
    public void assertThatCityCanBeFound() {
        Optional<CityDTO> city = addressService.getByCity("Melbourne");
        assertThat(city).isNotNull();
        assertThat(city.get().getName()).isEqualTo("Melbourne");
    }

}
