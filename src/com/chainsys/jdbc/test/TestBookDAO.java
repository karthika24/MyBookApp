package com.chainsys.jdbc.test;

import java.time.LocalDate;
import java.util.Scanner;

import com.chainsys.jdbc.Book;
import com.chainsys.jdbc.BookDAO;
import com.chainsys.jdbc.BookValidator;
import com.chainsys.jdbc.DisplayAll;

public class TestBookDAO {
	/*
	 * precondition id,name,price must be valid
	 */

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BookDAO bookDAO = new BookDAO();
		Book book = new Book();
		BookValidator validator = new BookValidator();
		DisplayAll display = new DisplayAll();
		char ch;
		do {
			System.out
					.println("Enter choices\n1.Add\n2.Update\n3.Delete\n4.FindById\n5.FindAll");
			int n = scanner.nextInt();

			switch (n) {
			case 1:
				book = new Book();
				//System.out.println("Enter id to insert");
				//int id = scanner.nextInt();
				//book.id = id;  because we using sequences
				System.out.println("Enter name to insert");
				String name = scanner.next();
				book.name = name;
				System.out.println("Enter price to insert");
				int price = scanner.nextInt();
				book.price = price;
				System.out.println("Enter date to insert (yyyy-mm-dd)");
				String date = scanner.next();
				book.publish_date = LocalDate.parse(date);
				try {
					validator.validateAdd(book);
					bookDAO.addBook(book);
				} catch (Exception e) {
					e.printStackTrace();
					// System.out.println(e.getMessage());
				}
				display.displayAll();
				break;

			case 2:
				System.out.println("Enter id to update");
				int id1 = scanner.nextInt();
				book = new Book();
				book.id = id1;
				// check for id available using Findbyid
				try {
					validator.validateUpdate(book);

					book = bookDAO.findById(book);
					if (book == null) {
						System.out.println("No record found..!");
					} else {
						System.out.println("Enter name update");
						String name1 = scanner.next();
						book.name = name1;
						bookDAO.updateBook(book);
						display.displayAll();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;

			case 3:
				System.out.println("Enter id to delete");
				int id2 = scanner.nextInt();
				book = new Book();
				book.id = id2;
				try {
					validator.validateUpdate(book);

					book = bookDAO.findById(book);
					if (book == null) {
						System.out.println("No record found..!");
					} else {
						bookDAO.deleteBook(book);
						display.displayAll();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;

			case 4:
				System.out.println("Enter id to find");
				int id3 = scanner.nextInt();
				book = new Book();
				book.id = id3;
				try {
					validator.validateUpdate(book);
					book = bookDAO.findById(book);
					if (book == null) {
						System.out.println("No record found");
					} else {
						System.out.print(book.id + " ");
						System.out.print(book.name + " ");
						System.out.print(book.price);
						System.out.println(book.publish_date);
						System.out.println();
					}
				} catch (Exception e) {
					e.printStackTrace();
					e.getMessage();// to know the exact exception
				}
				break;

			case 5:
				display.displayAll();
				break;
			}
			System.out.println("If you want to continue press y else n");
			ch = scanner.next().charAt(0);
		} while (ch == 'y');

		scanner.close();
	}

}
