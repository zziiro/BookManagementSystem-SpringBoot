package com.library.bookmanagement.Book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/book")
@RestController
@Slf4j
public class CustomerBookControllerV1 {

    private CustomerBookService customerBookService;

    // constructors
    @Autowired
    public CustomerBookControllerV1(CustomerBookService customerBookService){
        this.customerBookService = customerBookService;
    }

    public CustomerBookControllerV1(){}

    /* GET ROUTES */
    @GetMapping()
    public String homePage(){
        return "Book Controller Home Page!";
    }

    @GetMapping("/allbooks")
    public List<Book> getAllBooks(){
        log.info("Attempting to retrieve all books..");
        return customerBookService.getBooks();
    }

    @GetMapping("/bookbyauthor")
    public List<Book> getBooksByAuthor(
            @RequestBody String author){
        log.info("Attempting to retrieve books written by " + author);
        return customerBookService.getBookByAuthor(author);
    }

    @GetMapping("/bookbypublisher")
    public List<Book> getBooksByPublisher(@RequestBody String publisher){
        log.info("Attempting to retrieve books published by " + publisher);
        return customerBookService.getBookByPublisher(publisher);
    }
}
