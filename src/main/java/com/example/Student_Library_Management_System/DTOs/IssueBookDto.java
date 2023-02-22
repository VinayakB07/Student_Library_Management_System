package com.example.Student_Library_Management_System.DTOs;

public class IssueBookDto {
    private int bookId;
    private int cardId;

    public IssueBookDto() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
