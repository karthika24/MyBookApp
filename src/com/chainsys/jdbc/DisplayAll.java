package com.chainsys.jdbc;

import java.util.ArrayList;

public class DisplayAll {
	BookDAO bookDAO = new BookDAO();
	public void displayAll() {
		ArrayList<Book> booklist = bookDAO.findAll();
		if (booklist.size() == 0) {
			System.out.println("No records..!");
		} else {
			System.out.println("ID NAME PRICE DATE");
			System.out.println("-------------");
			for(Book b : booklist) {
			System.out.print(b.id + " ");
			System.out.print(b.name+ " ");
			System.out.print(b.price+" ");
			System.out.println(b.publish_date);
			System.out.println();
			}
		}
	}

}
