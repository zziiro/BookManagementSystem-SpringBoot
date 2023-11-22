package com.library.bookmanagement.Book;

import com.library.bookmanagement.Admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/v1/admin/book")
@RestController
@Slf4j
public class AdminBookController {

    private AdminBookService adminBookService;

    @Autowired
    public AdminBookController(AdminBookService adminBookService){

        this.adminBookService = adminBookService;
    }

    /* ROUTES FOR BOOK CRUD OPERATIONS */

    /* POST ROUTES */
    @PostMapping("/addNewBook")
    public void addNewBook(@Valid @RequestBody Book book){
        log.info("[AdminBookController.addNewBook() method called]");
        adminBookService.addNewBook(book);
    }

    /* PUT ROUTES */
    @PutMapping(path = "updateBookInventory/{bookId}")
    public void updateBookCount(
            @PathVariable("bookId") Long id,
            @RequestParam(required = true) Integer amountInInventory
    ){
        log.info("[AdminBookController.updateBookCount() method has been called]");
        adminBookService.updateBookInventory(id, amountInInventory);
    }

    @PutMapping(path = "removeBookInventory/{bookId}")
    public void removeBookInventory(
            @PathVariable("bookId") Long id
    ){
        log.info("[AdminBookController.removeBookInventory with id: [" + id + "] method has been called]");
        adminBookService.removeBookInventory(id);
    }

    /* DELETE ROUTES */
    @DeleteMapping(path = "removebook/{bookId}")
    public void deleteBook(
            @PathVariable("bookId") Long id
    ){
        log.info("[AdminBookController.deleteBook() with id: [" + id + "] has been called]");
        adminBookService.deleteEntireBookInstance(id);
    }
}
