package com.chainsys.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {

	public void addBook(Book book) throws Exception {
		try {
			Connection connection = ConnectionUtil.getConnection();
			String sql = "insert into books (id,name,price,publish_date) values (book_id_sqnc.nextval,?,?,?)";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			// preparedStatement.setInt(1, id); because we use sequence for
			// generating id
			//preparedStatement.setInt(1, book.id);
			preparedStatement.setString(1, book.name);
			preparedStatement.setInt(2, book.price);
			// converting local date to sql date
			preparedStatement.setDate(3, Date.valueOf(book.publish_date));
			int rows = preparedStatement.executeUpdate();
			System.out.println("Rows inserted: " + rows);
			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new Exception("Unable to insert book");
		}

	}

	public void updateBook(Book book) {
		try {
			Connection connection = ConnectionUtil.getConnection();
			String sql = "update books set name =? where id=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, book.name);
			preparedStatement.setInt(2, book.id);
			int rows = preparedStatement.executeUpdate();

			System.out.println("Rows updated: " + rows);

			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println(e.getMessage());
			// throw new Exception("Unable to update book");
		}

	}

	public void deleteBook(Book book) {
		try {
			Connection connection = ConnectionUtil.getConnection();

			String sql = "delete from books where id=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, book.id);
			int rows = preparedStatement.executeUpdate();

			System.out.println("Rows affected: " + rows);

			ConnectionUtil.close(connection, preparedStatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			// throw new Exception("Unable to delete book");
		}

	}

	public ArrayList<Book> findAll() {
		ArrayList<Book> bookList = new ArrayList<Book>();
		try {

			Connection connection = ConnectionUtil.getConnection();
			String sql = "select id,name,price,publish_date from books order by id asc";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				book.id = resultSet.getInt("id");
				book.name = resultSet.getString("name");
				book.price = resultSet.getInt("price");
				Date date = resultSet.getDate("publish_date");
				//System.out.println(date);
				if (date == null) {
					book.publish_date = null;
				} else {
					book.publish_date = resultSet.getDate("publish_date")
							.toLocalDate();
				}
				bookList.add(book);
			}

			ConnectionUtil.close(connection, preparedStatement, resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new Exception("Unable to find book");
		}
		return bookList;

	}

	public Book findById(Book book) {
		try {
			Connection connection = ConnectionUtil.getConnection();
			String sql = "select id,name,price from books where id=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, book.id);
			ResultSet resultSet = preparedStatement.executeQuery();
			book = null;
			if (resultSet.next()) {
				book = new Book();
				book.id = resultSet.getInt("id");
				book.name = resultSet.getString("name");
				book.price = resultSet.getInt("price");
				Date date = resultSet.getDate("publish_date");
				//System.out.println(date);
				if (date == null) {
					book.publish_date = null;
				} else {
				book.publish_date = resultSet.getDate("publish_date")
						.toLocalDate();
				}

			}
			ConnectionUtil.close(connection, preparedStatement, resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new Exception("Unable to find book");
		}
		return book;

	}

}
