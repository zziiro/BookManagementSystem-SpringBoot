package com.library.bookmanagement;

import com.library.bookmanagement.Book.Book;
import com.library.bookmanagement.Book.BookRepository;
import com.library.bookmanagement.Book.CustomerBookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerBookServiceTest {

    @Autowired
    private BookRepository bookRepository;
    private CustomerBookService methodUnderTest;

    @BeforeEach
    public void setUp() {
        methodUnderTest = new CustomerBookService(bookRepository);
    }

    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void CustomerBookService_getBooksTest(){
        // given
        Book testBook1 = new Book(
                1L,
                "Author-test",
                "Publisher-test",
                "Title-test",
                1,
                1,
                1,
                "Summary-test"
        );
        Book testBook2 = new Book(
                2L,
                "Author-test",
                "Publisher-test",
                "Title-test",
                2,
                2,
                2,
                "Summary-test"
        );
        bookRepository.saveAll(Arrays.asList(testBook1, testBook2));

        // when
        List<Book> testBooks = methodUnderTest.getBooks();

        // then
        assertEquals(testBooks.size(), testBooks.size());

    }

    @Test
    void CustomerBookService_getBooksByAuthorTest(){
        Book testBook = new Book(
                1L,
                "Author-test",
                "Publisher-test",
                "Title-test",
                1,
                1,
                1,
                "Summary-test"
        );
        bookRepository.save(testBook);

        // when
        List<Book> testBookByAuthor =
                methodUnderTest.getBookByAuthor("Author-test");

        // then
        assertEquals(1, testBookByAuthor.size());
    }

    @Test
    void CustomerBookService_getBookByPublisherTest(){
        Book testBook = new Book(
                1L,
                "Author-test",
                "Publisher-test",
                "Title-test",
                1,
                1,
                1,
                "Summary-test"
        );
        bookRepository.save(testBook);

        // when
        List<Book> actualBookByPublisher =
                methodUnderTest.getBookByPublisher("Publisher-test");

        // then
        assertEquals(1, actualBookByPublisher.size());
    }

}