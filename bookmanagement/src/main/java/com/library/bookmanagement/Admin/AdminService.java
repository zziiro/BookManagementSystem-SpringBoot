package com.library.bookmanagement.Admin;

import com.library.bookmanagement.Book.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Objects;

@Service
@Slf4j
public class AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public AdminService(){}

    @PostMapping
    public Admin createNewAdmin(Admin admin){
        log.info("AdminService.createNewAdmin() method called..");
        return adminRepository.save(admin);
    }

    @PutMapping
    @Transactional
    public Admin updateAdminFirstName(Long id, String firstName){
        log.info("AdminService.updateAdminFirstName() method has been called");

        // find the admin by id
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Admin by that id [" + id + "] does not exist.."
                ));

        // validate requested update
        if(firstName == null
                || Objects.equals(admin.getLastName(), firstName)){
            throw new IllegalStateException(
                    "Unable to update.."
            );
        }

        // set the new first name
        admin.setFirstName(firstName);
        log.info("Admin first name has been updated to: " + firstName);
        return admin;
    }

    @PutMapping
    @Transactional
    public Admin updateAdminLastName(Long id, String lastName){
        log.info("AdminService.updateAdminLastName() method has been called");

        // find admin by id
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Admin by that id [" + id + "] does not exist.."
                ));

        // validate the requested change
        if(lastName == null
            || Objects.equals(admin.getLastName(), lastName)){
            throw new IllegalStateException(
                    "Unable to update.."
            );
        }

        // set the new last name
        admin.setLastName(lastName);
        log.info("Admin last name has been updated to: " + lastName);
        return admin;
    }
}
