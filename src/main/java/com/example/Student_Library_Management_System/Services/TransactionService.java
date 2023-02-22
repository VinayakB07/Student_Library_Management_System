package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookDto issueBookDto) throws Exception{
        int bookId=issueBookDto.getBookId();
        int cardId=issueBookDto.getCardId();

        Book book=bookRepository.findById(bookId).get();
        Card card=cardRepository.findById(cardId).get();

        Transactions transactions=new Transactions();
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setIssueOperation(true);
        transactions.setTransactionStatus(TransactionStatus.PENDING);

        if(book==null|| book.isIssued()==true){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book is not Available");
        }
        if(card==null||card.getCardStatus()!= CardStatus.ACTIVATED){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("card is not valid");
        }
        transactions.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(true);

        List<Transactions> listOfTransactionsForBook=book.getTransactionList();
        listOfTransactionsForBook.add(transactions);
        book.setTransactionList(listOfTransactionsForBook);

        List<Book> issuedBooksForCard=card.getBooksIssued();
        issuedBooksForCard.add(book);
        card.setBooksIssued(issuedBooksForCard);

        List<Transactions> listOfTransactionsForCard=card.getTransactionList();
        listOfTransactionsForCard.add(transactions);
        card.setTransactionList(listOfTransactionsForCard);

        cardRepository.save(card);
        return "Book issued successfully";

    }
}
