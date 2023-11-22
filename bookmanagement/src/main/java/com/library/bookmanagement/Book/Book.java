package com.library.bookmanagement.Book;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;

@Entity(name = "Book")
@Table(name = "book")
@Getter
@Setter
@ToString
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long Id;

    @Column(name = "author", columnDefinition = "TEXT")
    @NotNull
    private String author;

    @Column(name = "publisher", columnDefinition = "TEXT")
    @NotNull
    private String publisher;

    @Column(name = "title", columnDefinition = "TEXT")
    @NotNull
    private String title;

    @Column(name = "pagecount", columnDefinition = "TEXT")
    @NotNull
    private Integer pageCount;

    @Column(name = "price", columnDefinition = "INTEGER")
    @NotNull
    private Integer price;

    @Column(name = "amountininventory", columnDefinition = "INTEGER")
    @NotNull
    private Integer amountInInventory;

    @Column(name = "summary", columnDefinition = "INTEGER")
    private String summary;

    // All args constructor
    public Book(Long Id, String author, String publisher, String title,
                Integer pageCount, Integer price,
                Integer amountInInventory, String summary){
        this.Id = Id;
        this.author = author;
        this.publisher = publisher;
        this.title = title;
        this.pageCount = pageCount;
        this.price = price;
        this.amountInInventory = amountInInventory;
        this.summary = summary;
    }

    // no id constructor
    public Book(String author, String publisher, String title,
                Integer pageCount, Integer price,
                Integer amountInInventory, String summary){
        this.author = author;
        this.publisher = publisher;
        this.title = title;
        this.pageCount = pageCount;
        this.price = price;
        this.amountInInventory = amountInInventory;
        this.summary = summary;
    }

    // no args constructor
    public Book(){}
}
