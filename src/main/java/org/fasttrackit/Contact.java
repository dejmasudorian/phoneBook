package org.fasttrackit;

import org.fasttrackit.domain.PhoneBook;
import org.fasttrackit.service.PhoneBookService;
import org.fasttrackit.transfer.CreatePhoneBook;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Contact {

    private PhoneBook phoneBook;
    private PhoneBookService phoneBookService;



    public void start() throws Exception {

        createPhoneBookInTable();
        System.out.println();
        System.out.println("This is your contact info: " + phoneBook.toString());
        System.out.println();
        phoneBookService.readPhoneBook();
        System.out.println();
        System.out.println("If you wish to find a person in the Phone Book, please type the First name or Last Name:");
        String name = typeStringFromUser();
        insertPhoneBook(name);
        System.out.println("If you wish to edit an existing contact in the phone book please enter:");
        System.out.println("1 for Yes;");
        System.out.println("0 for No.");
        int verify = typeIntFromUser();
        if (verify == 1)
        {
            System.out.println("Please enter the First Name of the contact you wish to edit.");
            String nameVerify = typeStringFromUser();
            editPhoneBook(nameVerify);
            phoneBookService.readPhoneBook();
        } else if (verify == 0) System.out.println("No edit required.");

        System.out.println("If you wish to delete an existing contact in the phone book please enter:");
        System.out.println("1 for Yes;");
        System.out.println("0 for No.");
        int verifyDel = typeIntFromUser();
        if (verifyDel == 1)
        {
            System.out.println("Please enter the First Name of the contact you wish to delete.");
            String nameDel = typeStringFromUser();
            CreatePhoneBook createPhoneBook = new CreatePhoneBook();
            phoneBookService.deletePhoneBook(createPhoneBook, nameDel);
            phoneBookService.readPhoneBook();
        } else if (verifyDel == 0) System.out.println("No delete required.");

        System.out.println("If you wish to delete mutiple contacts from the list please enter:");
        System.out.println("1 for Yes;");
        System.out.println("0 for No.");
        int verifyMulDel = typeIntFromUser();
        if (verifyMulDel == 1)
        {
            System.out.println("Please enter the first Letter of names you wish to delete from the list.");
            String nameMulDel = typeStringFromUser();
            CreatePhoneBook createPhoneBook = new CreatePhoneBook();
            phoneBookService.deleteMultiplePhoneBook(createPhoneBook, nameMulDel);
            phoneBookService.readPhoneBook();
        } else if (verifyMulDel == 0) System.out.println("No delete required.");

        System.out.println("Goodbye.");

    }


    public PhoneBook createPhoneBookInTable() throws Exception {
        PhoneBook phoneBook = new PhoneBook();
        System.out.println("Please enter your contact data:");
        System.out.println("First Name:");
        phoneBook.setFirst_name(typeStringFromUser());
        System.out.println("Last Name:");
        phoneBook.setLast_name(typeStringFromUser());
        System.out.println("Phone number:");
        phoneBook.setPhone_number(typeStringFromUser());
        System.out.println("Email:");
        phoneBook.setEmail(typeStringFromUser());

        return phoneBook;
    }

    public void insertPhoneBook(String name) throws Exception {
        List<PhoneBook> phoneBooks = phoneBookService.readPhoneBook();
        PhoneBook phoneBook1 = phoneBooks.stream().filter(a -> a.getFirst_name().equals(name)).findFirst().orElse(null);
        PhoneBook phoneBook2 = phoneBooks.stream().filter(a -> a.getLast_name().equals(name)).findFirst().orElse(null);
        CreatePhoneBook createPhoneBook = new CreatePhoneBook();
        if (phoneBook1 == null)
        {
            phoneBookService.createPhoneBook(createPhoneBook);
        }
        else this.phoneBook = phoneBook1;
        if(phoneBook2 == null)
        {
            phoneBookService.createPhoneBook(createPhoneBook);
        }
        else this.phoneBook = phoneBook2;
    }

    public void editPhoneBook(String name) throws Exception {
        List<PhoneBook> phoneBooks = phoneBookService.readPhoneBook();
        CreatePhoneBook createPhoneBook = new CreatePhoneBook();
        PhoneBook phoneBook = phoneBooks.stream().filter(a -> a.getFirst_name().equals(name)).findFirst().orElse(null);
        if (phoneBook == null)
        {
            phoneBook = createPhoneBookInTable();
            phoneBookService.updatePhoneBook(createPhoneBook);}
        this.phoneBook = phoneBook;
    }

    private String typeStringFromUser() throws Exception {
        Scanner scanner = new Scanner(System.in);
        try { return scanner.nextLine();

        } catch (InputMismatchException exception){
            System.out.println("Please enter a valid string:");
            return typeStringFromUser();
        }
    }

    private int typeIntFromUser(){
        Scanner scanner = new Scanner(System.in);
        try {
            return  scanner.nextInt();}
        catch (InputMismatchException e) {
            System.out.println("Please enter a valid an Integer variable:");
            return typeIntFromUser();
        }
    }

    public Contact(){
        this.phoneBookService = new PhoneBookService();
    }
}
