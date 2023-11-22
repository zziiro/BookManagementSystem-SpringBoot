package com.library.bookmanagement.Customer;

import com.library.bookmanagement.Book.Book;
import com.library.bookmanagement.Book.CustomerBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/customer")
@RestController
@Slf4j
public class CustomerControllerV1 {

    private CustomerService customerService;
    private CustomerBookService customerBookService;

    // constructors
    @Autowired
    public CustomerControllerV1(CustomerService customerService,
                                CustomerBookService customerBookService){
        this.customerService = customerService;
        this.customerBookService = customerBookService;
    }

    public CustomerControllerV1(){}

    /* LINKS WITH SERVICE CLASSES */
    /* Customer Route creation */

    /* GET ROUTES */
    @GetMapping
    public String homePage(){
        return "Book Management System | " +
                "Written in Java SpringBoot | " +
                "Using PostgreSQL and Java Spring Data Jpa";
    }
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        log.info("Attempting to retrieve all books...");
        return customerBookService.getBooks();
    }

    /* POST ROUTES */
    @PostMapping("/login")
    public void customerLogIn(@RequestBody Customer customer){
        log.info("Attempting customer log in...");
        customerService.logIn(customer);
    }

    @PostMapping("/createaccount")
    public void createNewAccount(@Valid @RequestBody Customer customer){
        log.info("Attempting to create a new account...");
        customerService.createNewAccount(customer);
    }

    /* PUT ROUTES / UPDATE ROUTES */
    @PutMapping(path = "username/{customerId}")
    public void customerUpdateUsername(
            @PathVariable("customerId") Long id,
            @RequestParam(required = true) String username
    ){
        log.info("Attempting to update customer username...");
        customerService.updateUsername(id, username);
    }

    @PutMapping(path = "password/{customerId}")
    public void customerUpdatePassword(
            @PathVariable("customerId") Long id,
            @RequestParam(required = true) String password
    ){
        log.info("Attempting to update customer password");
        customerService.updatePassword(id, password);
    }

    /* DELETE ROUTES */
    @DeleteMapping(path = "delete/{customerId}")
    public void customerDeleteAccount(@PathVariable("customerId") Long id){
        log.info("Attempting to delete customer account...");
        customerService.deleteAccount(id);
    }

}
