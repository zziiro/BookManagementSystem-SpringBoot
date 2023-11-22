package com.library.bookmanagement.Customer;

import com.library.bookmanagement.Book.Book;
import com.library.bookmanagement.Book.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    /* LINKS WITH THE CUSTOMER REPOSITORY INTERFACE */
    // access the database methods in this class then use CustomerControllerV1 to link them to the user

    private CustomerRepository customerRepository;
    private BookRepository bookRepository;

    // constructors
    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           BookRepository bookRepository){
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    public CustomerService(){}

    // POST ROUTES
    @PostMapping
    public Customer logIn(Customer customer){
        log.info("[POST LOGGER] CustomerService.logIn() method has been called.");

        // get customer by username and password
        Optional<Customer> customerByUsernameAndPassword = customerRepository
                .findCustomerByUsernameAndPassword(
                        customer.getUsername(), customer.getPassword());

        // check if everything matches
        if(customerByUsernameAndPassword.isEmpty()){
            throw new IllegalStateException(
                    "[ERROR] Customer does not exist.."
            );
        }

        // if log in successful (redirect them to homepage if possible
        log.info("Customer has been logged in.");
        return customer;
    }

    @PostMapping
    public Customer createNewAccount(Customer customer){
        log.info("[POST LOGGER] CustomerService.createNewAccount() method has been called.");
        return customerRepository.save(customer);
    }

    // PUT ROUTES
    @PutMapping
    @Transactional
    public Customer updateUsername(Long id, String username){
        log.info("[PUT LOGGER] CustomerService.updateUsername() method has been called..");
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Customer does not exist"
                ));

        // validate new username request
        if(username == null
                || username.length() <= 0
                || Objects.equals(customer.getUsername(), username)){
                throw new IllegalStateException(
                    "Illegal username"
            );
        }
        customer.setUsername(username);
        log.info("New Customer Username has been Updated");
        return customer;
    }

    @PutMapping
    @Transactional
    public void updatePassword(Long id, String password){
        log.info("[PUT LOGGER] CustomerService.updatePassword() method called..");
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Customer does not exist"
                ));

        // validate new password request
        if(password == null
                || password.length() <= 0
                || Objects.equals(customer.getPassword(), password)){
            throw new IllegalStateException(
                    "Illegal password"
            );
        }
        customer.setPassword(password);
        log.info("New Customer Password has been Updated");
    }

    // DELETE ROUTES
    @DeleteMapping
    public void deleteAccount(Long id){
        log.info("[DELETE LOGGER] CustomerService.deleteAccount() method called..");
        boolean customerExists = customerRepository.existsById(id);

        if(!customerExists){
            throw new IllegalStateException(
                    "Customer with ID of [" + id + "] does not exist"
            );
        }

        // log that customer account has been deleted
        log.info("Customer account with id of [" + id + "] has been deleted successfully");
        customerRepository.deleteById(id);
    }

}
