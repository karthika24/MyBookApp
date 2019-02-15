package com.chainsys.jdbc;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookDAOTest {

	 @BeforeClass
	 public static void setUpBeforeClass() throws Exception {
	 System.out.println("BeforeClass");
	 }
	
	 @AfterClass
	 public static void tearDownAfterClass() throws Exception {
	 System.out.println("AfterClass");
	 }
	
	 @Before
	 public void setUp() throws Exception {
	 System.out.println("Before");
	 }
	
	 @After
	 public void tearDown() throws Exception {
	 System.out.println("After");
	 }

	@Test
	public void testAddBook() throws Exception {
		BookDAO bookDAO = new BookDAO();
		Book book = new Book();
		book.id = 28;
		book.name = "java";
		book.price = 400;
		book.publish_date = LocalDate.parse("2019-01-01");
		bookDAO.addBook(book);

		Book book1 = new Book();
		book1.id = 28;
		Book b = bookDAO.findById(book1);

		assertEquals(book.id, b.id);
		// fail("Not yet implemented");
	}

	@Test
	public void testUpdateBook() {
		BookDAO bookDAO = new BookDAO();
		Book book = new Book();
		book.id = 20;
		book.name = "java";
		bookDAO.updateBook(book);

		Book book1 = new Book();

		book1.id = 20;
		Book b = bookDAO.findById(book1);
		assertEquals(book.name, b.name);
		// fail("Not yet implemented");
	}

	@Test
	public void testDeleteBook() {
		BookDAO bookDAO = new BookDAO();
		Book book = new Book();
		book.id = 19;
		bookDAO.deleteBook(book);

		Book book1 = new Book();
		book1.id = 19;
		Book b = bookDAO.findById(book1);

		assertEquals(null, b);
		// fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		BookDAO bookDAO = new BookDAO();
		ArrayList<Book> booklist = bookDAO.findAll();
		assertNotEquals(0, booklist.size());
		// fail("Not yet implemented");
	}

	@Test
	public void testFindById() {

		BookDAO bookDAO = new BookDAO();
		Book book = new Book();
		book.id = 8;
		bookDAO.findById(book);

		Book book1 = new Book();
		book1.id = 8;
		Book b = bookDAO.findById(book1);
		assertEquals(book.id, b.id);
		// fail("Not yet implemented");
	}

}
