package org.fasttrackit.service;

import org.fasttrackit.domain.PhoneBook;
import org.fasttrackit.persistence.PhoneBookRepository;
import org.fasttrackit.transfer.CreatePhoneBook;
import org.fasttrackit.transfer.PhoneBookListResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PhoneBookService {

    private PhoneBookRepository phoneBookRepository =
            new PhoneBookRepository();

    public void createPhoneBook(CreatePhoneBook createPhoneBook) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating phone book numbers: " + createPhoneBook);
        phoneBookRepository.createPhoneBook(createPhoneBook);
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

    public void updatePhoneBook(CreatePhoneBook update) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating phone book entry." + update);
        phoneBookRepository.updatePhoneBook(update);
    }

    public void deletePhoneBook(CreatePhoneBook delete,String name) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting phone book entry." + delete);
        phoneBookRepository.deletePhoneBook(delete, name);
    }

    public void deleteMultiplePhoneBook(CreatePhoneBook deleteM,String letter) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting phone book entry." + deleteM);
        phoneBookRepository.deleteMultiplePhoneBook(deleteM, letter);
    }
}
