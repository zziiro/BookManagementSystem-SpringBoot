package com.library.bookmanagement;

import com.library.bookmanagement.Admin.Admin;
import com.library.bookmanagement.Admin.AdminRepository;
import com.library.bookmanagement.Admin.AdminService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AdminServiceTest {

    @Autowired
    private AdminRepository adminRepository;
    private AdminService methodUnderTest;

    @BeforeEach
    void setUp(){
        methodUnderTest = new AdminService(adminRepository);
    }

    @AfterEach
    void tearDown(){
        adminRepository.deleteAll();
    }

    @Test
    void AdminServiceCreateNewAdminTest(){
        Admin testAdmin = new Admin(
               1L,
               "firstname-test",
               "lastname-test",
               "test@gmail.com"
        );
        adminRepository.save(testAdmin);

        Admin actual = methodUnderTest.createNewAdmin(testAdmin);

        assertEquals(1, actual.getEmployeeId());
        assertEquals("firstname-test", actual.getFirstName());
        assertEquals("lastname-test", actual.getLastName());
        assertEquals("test@gmail.com", actual.getEmail());
    }

    @Test
    @Transactional
    void AdminServiceUpdateAdminFirstNameTest(){
        Admin testAdmin = new Admin(
                1L,
                "firstname-test",
                "lastname-test",
                "test@gmail.com"
        );
        adminRepository.save(testAdmin);

        Admin actual = methodUnderTest.updateAdminFirstName(
                testAdmin.getId(), "updated firstName");

        assertEquals("updated firstName", actual.getFirstName());


    }

    @Test
    @Transactional
    void AdminServiceUpdateAdminLastName(){
        Admin testAdmin = new Admin(
                1L,
                "firstname-test",
                "lastname-test",
                "test@gmail.com"
        );
        adminRepository.save(testAdmin);

        Admin actual = methodUnderTest.updateAdminLastName(
                testAdmin.getId(), "updated lastName");

        assertEquals("updated lastName", actual.getLastName());
    }
}
