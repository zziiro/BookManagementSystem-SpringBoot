package com.library.bookmanagement.Admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Entity(name = "Admin")
@Table(
        name = "admin",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "admin_email_unique",
                        columnNames = "email"
                )
        }
)
@Getter
@Setter
@ToString
public class Admin {
    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            sequenceName = "admin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admin_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "employeeid")
    @NotNull
    private Long employeeId;

    @Column(name = "firstname", columnDefinition = "TEXT")
    @NotNull
    private String firstName;

    @Column(name = "lastname", columnDefinition = "TEXT")
    @NotNull
    private String lastName;

    @Column(name = "email", columnDefinition = "TEXT")
    @NotNull
    private String email;

    /* Constructors */
    // all args constructor
    public Admin(Long id, Long employeeId, String firstName,
                 String lastName, String email){
        this.id = id;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // no id constructor
    public Admin(Long employeeId, String firstName,
                  String lastName, String email){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // no args constructor
    public Admin(){}

}
