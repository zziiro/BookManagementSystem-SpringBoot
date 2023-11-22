package com.library.bookmanagement.Book;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Slf4j
public class CustomerBookService {

    private BookRepository bookRepository;

    // constructors
    @Autowired
    public CustomerBookService(BookRepository bookRepository){

        this.bookRepository = bookRepository;
    }

    public CustomerBookService(){}

    /* GET ROUTES */
    @GetMapping
    public List<Book> getBooks(){
        log.info("getBooks method called..");
        return bookRepository.findAll();
    }

    @GetMapping
    public List<Book> getBookByAuthor(String author){
        log.info("[GET LOGGER] BookService.getBookByAuthorName() has been called..");
        return bookRepository.findBookByAuthor(author);
    }

    @GetMapping
    public List<Book> getBookByPublisher(String publisher){
        log.info("[GET LOGGER] BookService.getBookByPublisher() has been called..");
        return bookRepository.findBookByPublisher(publisher);
    }

}
