package com.library.bookmanagement;

import com.library.bookmanagement.Customer.Customer;
import com.library.bookmanagement.Customer.CustomerRepository;
import com.library.bookmanagement.Customer.CustomerService;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CustomerServiceTest {
    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService methodUnderTest;

    @BeforeEach
    void setUp(){
        methodUnderTest = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown(){
        customerRepository.deleteAll();
    }

    @Test
    void CustomerServiceLogInTest(){
        // given
        Customer testCustomer = new Customer(
                1L,
                "firstname-test lastname-test",
                "test@gmail.com",
                "username-test",
                "password-test"
        );
        customerRepository.save(testCustomer);

        // when
        Customer actual = methodUnderTest.logIn(testCustomer);

        // then
        assertEquals("username-test", actual.getUsername());
        assertEquals("password-test", actual.getPassword());
    }

    @Test
    void CustomerServiceCreateNewAccountTest(){
        // given
        Customer testCustomer = new Customer(
                1L,
                "firstname-test lastname-test",
                "test@gmail.com",
                "username-test",
                "password-test"
        );
        customerRepository.save(testCustomer);

        Customer actual = methodUnderTest.createNewAccount(testCustomer);

        assertEquals(1L, actual.getId());
        assertEquals("firstname-test lastname-test",
                actual.getFullName());
        assertEquals("username-test",
                actual.getUsername());
        assertEquals("password-test",
                actual.getPassword());
    }

    @Test
    void CustomerServiceUpdateUsernameTest(){
        // given
        Customer testCustomer = new Customer(
                1L,
                "firstname-test lastname-test",
                "test@gmail.com",
                "username-test",
                "password-test"
        );
        customerRepository.save(testCustomer);

        Customer actual = methodUnderTest.updateUsername(testCustomer.getId(),
                "new-test-username");

        assertEquals("new-test-username", actual.getUsername());
    }
}
