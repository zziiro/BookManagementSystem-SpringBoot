package com.library.bookmanagement;

import com.github.javafaker.Faker;
import com.library.bookmanagement.Admin.Admin;
import com.library.bookmanagement.Admin.AdminRepository;
import com.library.bookmanagement.Book.Book;
import com.library.bookmanagement.Book.BookRepository;
import com.library.bookmanagement.Customer.Customer;
import com.library.bookmanagement.Customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class BookManagementApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BookManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
										BookRepository bookRepository,
										AdminRepository adminRepository){
		return args -> {
			// for making fake instances of database items
		};
	}

	private void createFakeCustomers(CustomerRepository customerRepository){
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String fullname = firstName + " " + lastName;
		String email = fullname + "@gmail.com";
		String username = firstName + "username";
		String password = faker.internet().password();
		Customer customer = new Customer(
				fullname,
				email,
				username,
				password
		);
		customerRepository.save(customer);
	}

	private void createFakeBookInstances(BookRepository bookRepository){
		Random random = new Random();
		int upperBound = 3;
		int pageCountUpperBound = 500;
		int priceUpperBound = 20;
		int randomBookPublisher = random.nextInt(upperBound);
		int randomPageCount = random.nextInt(pageCountUpperBound);
		int randomPrice = random.nextInt(priceUpperBound);
		String[] bookPublishers = {"Penguin Classics", "Simon and Schuster",
				"Macmillan Publishers", "Hachette Book Group"};

		// faker variables
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String author = firstName + " " + lastName;
		String publisher = bookPublishers[randomBookPublisher];
		Integer pageCount = randomPageCount;
		String title = author + ": Book Title";
		Integer price = randomPrice;
		Integer amountInInventory = randomPageCount;
		String summary = "Book Summary Section";

		// send fake data to book entity
		Book book = new Book(
				author,
				publisher,
				title,
				pageCount,
				price,
				amountInInventory,
				summary
		);

		// save the book object to the database
		bookRepository.save(book);
	}

	public void createFakeAdmins(AdminRepository adminRepository){
		Faker faker = new Faker();
		Long employeeId = faker.number().randomNumber();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String email = firstName + lastName + "@gmail.com";
		Admin admin = new Admin(
				employeeId,
				firstName,
				lastName,
				email
		);
		adminRepository.save(admin);
	}
}
