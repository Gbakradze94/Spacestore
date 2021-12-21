package com.onlinestore.customer;


import com.onlinestore.common.entity.AuthenticationType;
import com.onlinestore.common.entity.Country;
import com.onlinestore.common.entity.Customer;
import com.onlinestore.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTests {
    @Autowired
    private CustomerRepository repo;
    @Autowired private TestEntityManager entityManager;

    @Test
    public void testCreateCustomer1() {
        Integer countryId = 234; // USA
        Country country = entityManager.find(Country.class, countryId);

        Customer customer = new Customer();
        customer.setCountry(country);
        customer.setFirstName("Giorgi");
        customer.setLastName("Bakradze");
        customer.setPassword("random123");
        customer.setEmail("gbakradze@gmail.com");
        customer.setPhoneNumber("001002003");
        customer.setAddressLine1("1000  Higgins Drive");
        customer.setCity("London");
        customer.setState("UK");
        customer.setPostalCode("01992");
        customer.setCreatedTime(new Date());

        Customer savedCustomer = repo.save(customer);

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateCustomer2() {
        Integer countryId = 106; // India
        Country country = entityManager.find(Country.class, countryId);

        Customer customer = new Customer();
        customer.setCountry(country);
        customer.setFirstName("Some");
        customer.setLastName("Guy");
        customer.setPassword("somepassword");
        customer.setEmail("what@gmail.com");
        customer.setPhoneNumber("0122032");
        customer.setAddressLine1("112 , Birch Street.");
        customer.setAddressLine2("East side");
        customer.setCity("New York");
        customer.setState("NY");
        customer.setPostalCode("22993");
        customer.setCreatedTime(new Date());

        Customer savedCustomer = repo.save(customer);

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isGreaterThan(0);
    }

    @Test
    public void testListCustomers() {
        Iterable<Customer> customers = repo.findAll();
        customers.forEach(System.out::println);

        assertThat(customers).hasSizeGreaterThan(1);
    }

    @Test
    public void testUpdateCustomer() {
        Integer customerId = 1;
        String lastName = "Hetfield";

        Customer customer = repo.findById(customerId).get();
        customer.setLastName(lastName);
        customer.setEnabled(true);

        Customer updatedCustomer = repo.save(customer);
        assertThat(updatedCustomer.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void testGetCustomer() {
        Integer customerId = 2;
        Optional<Customer> findById = repo.findById(customerId);

        assertThat(findById).isPresent();

        Customer customer = findById.get();
        System.out.println(customer);
    }

    @Test
    public void testDeleteCustomer() {
        Integer customerId = 2;
        repo.deleteById(customerId);

        Optional<Customer> findById = repo.findById(customerId);
        assertThat(findById).isNotPresent();
    }

    @Test
    public void testFindByEmail() {
        String email = "gbakradze@gmail.com";
        Customer customer = repo.findByEmail(email);

        assertThat(customer).isNotNull();
        System.out.println(customer);
    }

    @Test
    public void testFindByVerificationCode() {
        String code = "code_123";
        Customer customer = repo.findByVerificationCode(code);

        assertThat(customer).isNotNull();
        System.out.println(customer);
    }

    @Test
    public void testEnableCustomer() {
        Integer customerId = 1;
        repo.enable(customerId);

        Customer customer = repo.findById(customerId).get();
        assertThat(customer.isEnabled()).isTrue();
    }

    @Test
    public void testUpdateAuthenticationType() {
        Integer id = 1;
        repo.updateAuthenticationType(id, AuthenticationType.DATABASE);

        Customer customer = repo.findById(id).get();

        assertThat(customer.getAuthenticationType()).isEqualTo(AuthenticationType.DATABASE);
    }
}
