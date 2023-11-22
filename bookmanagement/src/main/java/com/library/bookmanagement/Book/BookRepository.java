package com.library.bookmanagement.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.author = ?1")
    List<Book> findBookByAuthor(String author);

    @Query("SELECT b FROM Book b WHERE b.publisher = ?1")
    List<Book> findBookByPublisher(String publisher);



}
