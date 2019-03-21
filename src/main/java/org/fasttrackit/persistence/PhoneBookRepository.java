package org.fasttrackit.persistence;

import org.fasttrackit.domain.PhoneBook;
import org.fasttrackit.transfer.CreatePhoneBook;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookRepository {

    public void createPhoneBook(CreatePhoneBook createPhoneBook) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            String insertSql = "INSERT INTO phone_book (id, first_name, last_name, `phone_number`) VALUES (?,?,?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setLong(1, createPhoneBook.getId());
            preparedStatement.setString(2, createPhoneBook.getFirst_name());
            preparedStatement.setString(3, createPhoneBook.getLast_name());
            preparedStatement.setString(4, createPhoneBook.getPhone_number());

            preparedStatement.executeUpdate();
        }
    }

    public List<PhoneBook> getPhoneBook() throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            String query =
                    "SELECT id, first_name, last_name, `phone_number` FROM phone_book ORDER BY id ASC;";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<PhoneBook> response = new ArrayList<>();

            while (resultSet.next()) {
                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setId(resultSet.getLong("id"));
                phoneBook.setFirst_name(resultSet.getString("first_name"));
                phoneBook.setLast_name(resultSet.getString("last_name"));
                phoneBook.setPhone_number(resultSet.getString("phone_number"));


                response.add(phoneBook);
            }

            return response;
        }
    }

        public void updatePhoneBook (CreatePhoneBook phoneBook) throws SQLException, IOException, ClassNotFoundException {
           try (Connection connection = DatabaseConfiguration.getConnection()) {
               String insertSql = "UPDATE phone_book SET id = ? , first_name = ?, last_name = ?, `phone_number`=? WHERE first_name=?;";
               PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
               preparedStatement.setLong(1, phoneBook.getId());
               preparedStatement.setString(2, phoneBook.getFirst_name());
               preparedStatement.setString(3, phoneBook.getLast_name());
               preparedStatement.setString(4, phoneBook.getPhone_number());
               preparedStatement.executeUpdate();

           }
        }

    public void deletePhoneBook (CreatePhoneBook phoneBook, String name) throws SQLException, IOException, ClassNotFoundException {
       try (Connection connection = DatabaseConfiguration.getConnection()) {
           String insertSql = "DELETE FROM phone_book WHERE first_name="+name+";";
           PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
           preparedStatement.executeUpdate();
       }
    }

    public void deleteMultiplePhoneBook (CreatePhoneBook phoneBook, String letter) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            String insertSql = "DELETE FROM phone_book WHERE CustomerName LIKE '"+letter+"%';";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.executeUpdate();
        }
    }
}
