package com.library.bookmanagement.Book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Service
@Slf4j
public class AdminBookService {

    private final BookRepository bookRepository;

    @Autowired
    public AdminBookService(BookRepository bookRepository){

        this.bookRepository = bookRepository;
    }

    /* CREATE A NEW BOOK INSTANCES */
    @PostMapping
    public Book addNewBook(Book book){
        log.info("AdminBookService.addNewBook() method has been called..");
        return bookRepository.save(book);
    }

    @PutMapping
    @Transactional
    public Book updateBookInventory(Long id, Integer amountInInventory){
        log.info("AdminBookService.updateBookInventory() method has been called..");

        // find the requested book to update by id
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Book by the given id of [" + id + "] does not exist"
                ));

        // validate the amount to update
        if (amountInInventory < 1
                || Objects.equals
                        (book.getAmountInInventory(), amountInInventory
                        )){
            throw new IllegalStateException(
                    "Unable to update book inventory.."
            );
        }

        // set the new amount
        book.setAmountInInventory(amountInInventory);
        log.info("Book Inventory has been updated!");
        return book;
    }

    @PutMapping
    @Transactional
    public Book removeBookInventory(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Book with the ID of [" + id + "] does no exist.."
                ));

        // set the new amount
        book.setAmountInInventory(0);
        log.info("There are now 0 books in Inventory");
        return book;
    }

    @DeleteMapping
    public void deleteEntireBookInstance(Long id){
        log.info("AdminBookService.deleteEntireBookInstance() method has been called");
        bookRepository.deleteById(id);
    }
}
