package com.library.bookmanagement.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom SQL Queries

    // Query for customer log in
    @Query("SELECT c FROM Customer c WHERE c.username = ?1 AND c.password = ?2")
    Optional<Customer> findCustomerByUsernameAndPassword(String username, String password);

}
