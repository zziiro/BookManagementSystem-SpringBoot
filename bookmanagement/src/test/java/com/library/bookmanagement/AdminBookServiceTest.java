package com.library.bookmanagement;

import com.library.bookmanagement.Book.AdminBookService;
import com.library.bookmanagement.Book.Book;
import com.library.bookmanagement.Book.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AdminBookServiceTest {

    @Autowired
    private BookRepository bookRepository;
    private AdminBookService methodUnderTest;

    @BeforeEach
    void setUp(){

        methodUnderTest = new AdminBookService(bookRepository);
    }
    @AfterEach
    void tearDown(){

        bookRepository.deleteAll();
    }

    @Test
    void AdminBookServiceAddNewBookTest(){
        Book testBook = new Book(
                "Author-test",
                "Publisher-test",
                "Title-test",
                1,
                1,
                1,
                "Summary-test"
        );
        bookRepository.save(testBook);

        Book actual = methodUnderTest.addNewBook(testBook);

        assertEquals("Author-test", actual.getAuthor());
        assertEquals("Publisher-test", actual.getPublisher());
        assertEquals("Title-test", actual.getTitle());
        assertEquals(1, actual.getPageCount());
        assertEquals(1, actual.getPrice());
        assertEquals(1, actual.getAmountInInventory());
        assertEquals("Summary-test", actual.getSummary());


    }

    @Test
    @Transactional
    void AdminBookServiceUpdateBookInventoryTest(){
        Book testBook = new Book(
                "Author-test",
                "Publisher-test",
                "Title-test",
                1,
                1,
                1,
                "Summary-test"
        );
        bookRepository.save(testBook);

        Book actual = methodUnderTest.updateBookInventory
                (testBook.getId(),2);

        assertEquals(2, actual.getAmountInInventory());
    }

    @Test
    void AdminBookServiceRemoveBookInventoryTest(){
        Book testBook = new Book(
                "Author-test",
                "Publisher-test",
                "Title-test",
                1,
                1,
                1,
                "Summary-test"
        );
        bookRepository.save(testBook);

        Book actual = methodUnderTest.removeBookInventory(testBook.getId());

        assertEquals(0, actual.getAmountInInventory());
    }
}
