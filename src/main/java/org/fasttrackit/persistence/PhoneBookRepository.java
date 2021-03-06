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
            String insertSql = "INSERT INTO phone_book ( first_name, last_name, `phone_number` , `email`) VALUES (?,?,?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, createPhoneBook.getFirst_name());
            preparedStatement.setString(2, createPhoneBook.getLast_name());
            preparedStatement.setString(3, createPhoneBook.getPhone_number());
            preparedStatement.setString(4, createPhoneBook.getEmail());

            preparedStatement.executeUpdate();
        }
    }

    public List<PhoneBook> getPhoneBook() throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            String query =
                    "SELECT id, first_name, last_name, `phone_number`, `email` FROM phone_book ORDER BY id ASC;";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<PhoneBook> response = new ArrayList<>();

            while (resultSet.next()) {
                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setId(resultSet.getLong("id"));
                phoneBook.setFirst_name(resultSet.getString("first_name"));
                phoneBook.setLast_name(resultSet.getString("last_name"));
                phoneBook.setPhone_number(resultSet.getString("phone_number"));
                phoneBook.setEmail(resultSet.getString("email"));


                response.add(phoneBook);
            }

            return response;
        }
    }

        public void updatePhoneBook (CreatePhoneBook phoneBook) throws SQLException, IOException, ClassNotFoundException {
           try (Connection connection = DatabaseConfiguration.getConnection()) {
               String insertSql = "UPDATE phone_book SET id = ? , first_name = ?, last_name = ?, `phone_number`=?, `email`=? WHERE id=?;";
               PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
               preparedStatement.executeUpdate();

           }
        }

    public void deletePhoneBook (String id) throws SQLException, IOException, ClassNotFoundException {
       try (Connection connection = DatabaseConfiguration.getConnection()) {
           String insertSql = "DELETE FROM phone_book WHERE id="+id+";";
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
