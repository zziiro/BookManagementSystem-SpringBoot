package com.library.bookmanagement.Customer;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;

@Entity(name = "Customer")
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "customer_email_unique",
                        columnNames = "email"
                )
        }
)
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )

    @Column(name = "id", updatable = false)
    @NotNull
    private Long Id;

    @Column(name = "fullname", columnDefinition = "TEXT")
    @NotNull
    private String fullName;

    @Column(name = "email", columnDefinition = "TEXT")
    @NotNull
    private String email;

    @Column(name = "username", columnDefinition = "TEXT")
    @NotNull
    private String username;

    @Column(name = "password", columnDefinition = "TEXT")
    @NotNull
    private String password;

    // all args constructor
    public Customer(Long Id, String fullName, String email,
                    String username, String password){
        this.Id = Id;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // no id constructor
    public Customer(String fullname, String email,
                    String username, String password){
        this.fullName = fullname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // no args constructor
    public Customer(){}

}
