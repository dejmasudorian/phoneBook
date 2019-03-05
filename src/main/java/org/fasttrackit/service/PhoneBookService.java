package org.fasttrackit.service;

import org.fasttrackit.domain.PhoneBook;
import org.fasttrackit.persistence.PhoneBookRepository;
import org.fasttrackit.transfer.PhoneBookListResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PhoneBookService {

    private PhoneBookRepository phoneBookRepository =
            new PhoneBookRepository();

    public void createPhoneBook(PhoneBook phoneBook) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating phone book numbers: " + phoneBook);
        phoneBookRepository.createPhoneBook(phoneBook);
    }

    public PhoneBookListResponse getPhoneBook() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving phone book numbers.");
        List<PhoneBook> phoneBooks = phoneBookRepository.getPhoneBook();
        return new PhoneBookListResponse(phoneBooks);
    }

    public List<PhoneBook> readPhoneBook() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Reading phone book table.");
        return phoneBookRepository.getPhoneBook();
    }

    public void updatePhoneBook(PhoneBook phoneBook) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating phone book entry." + phoneBook);
        phoneBookRepository.updatePhoneBook(phoneBook);
    }

    public void deletePhoneBook(PhoneBook phoneBook,String name) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting phone book entry." + phoneBook);
        phoneBookRepository.deletePhoneBook(phoneBook, name);
    }

    public void deleteMultiplePhoneBook(PhoneBook phoneBook,String letter) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting phone book entry." + phoneBook);
        phoneBookRepository.deleteMultiplePhoneBook(phoneBook, letter);
    }
}
