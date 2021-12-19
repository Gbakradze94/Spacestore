package com.onlinestore.address;

//TODO: Add address repository tests


import com.onlinestore.common.entity.Address;
import com.onlinestore.common.entity.Country;
import com.onlinestore.common.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class AddressRepositoryTests {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    void testAddNew() {
        Integer customerId = 5;
        Integer countryId = 234;

        Address newAddress = new Address();
        newAddress.setCustomer(new Customer(customerId));
        newAddress.setCountry(new Country(countryId));
        newAddress.setFirstName("Mike");
        newAddress.setLastName("Mayers");
        newAddress.setPhoneNumber("122-020-1992");
        newAddress.setAddressLine1("105 Sunny Lane");
        newAddress.setCity("New York");
        newAddress.setState("New York");
        newAddress.setPostalCode("10013");

        Address savedAddress = addressRepository.save(newAddress);

        assertThat(savedAddress).isNotNull();
        assertThat(savedAddress.getId()).isGreaterThan(0);

    }
}
