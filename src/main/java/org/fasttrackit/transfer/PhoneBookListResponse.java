package org.fasttrackit.transfer;

import org.fasttrackit.domain.PhoneBook;

import java.util.List;

public class PhoneBookListResponse {
    private List<PhoneBook> content;

    public PhoneBookListResponse(List<PhoneBook> content) {
        this.content = content;
    }

    public PhoneBookListResponse() {
        // used for unmarshalling
    }

    public List<PhoneBook> getContent() {
        return content;
    }

    public void setContent(List<PhoneBook> content) {
        this.content = content;
    }
}
