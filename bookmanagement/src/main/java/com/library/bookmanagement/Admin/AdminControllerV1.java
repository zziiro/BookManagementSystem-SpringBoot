package com.library.bookmanagement.Admin;

import com.library.bookmanagement.Book.AdminBookService;
import com.library.bookmanagement.Book.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/v1/admin")
@RestController
@Slf4j
public class AdminControllerV1 {

    private AdminService adminService;

    @Autowired
    public AdminControllerV1(AdminService adminService){

        this.adminService = adminService;
    }

    public AdminControllerV1(){}

    /* NORMAL ADMIN ROUTES */
    /* POST ROUTES */
    @PostMapping("/newadmin")
    public void createNewAdmin(@Valid @RequestBody Admin admin){
        log.info("Attempting to create a new admin..");
        adminService.createNewAdmin(admin);
    }

    @PutMapping(path = "updateAdminInformation/firstname/{adminId}")
    public void updateAdminFirstName(
            @PathVariable("adminId") Long id,
            @RequestParam(required = true) String firstName
    ){
        adminService.updateAdminFirstName(id, firstName);
    }

    @PutMapping(path = "updateAdminInformation/lastname/{adminId}")
    public void updateAdminLastName(
            @PathVariable("adminId") Long id,
            @RequestParam(required = true) String lastName
    ){
        adminService.updateAdminLastName(id, lastName);
    }
}
