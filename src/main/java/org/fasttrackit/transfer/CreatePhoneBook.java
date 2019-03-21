package org.fasttrackit.transfer;

import org.fasttrackit.domain.PhoneBook;

import java.util.List;

public class CreatePhoneBook {
    private long id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private List<PhoneBook> content;

    public CreatePhoneBook(List<PhoneBook> content) {
        this.content = content;
    }

    public CreatePhoneBook() {
        // used for unmarshalling
    }

    public List<PhoneBook> getContent() {
        return content;
    }

    public void setContent(List<PhoneBook> content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone_book='" + phone_number + '\'' +
                '}';
    }
}