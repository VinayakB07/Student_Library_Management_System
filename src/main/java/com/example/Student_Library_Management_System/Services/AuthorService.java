package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.DTOs.BookResponseDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDto authorEntryDto){

        Author author=new Author();

        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());
        author.setName(authorEntryDto.getName());

        authorRepository.save(author);
        return "author added successfully";
    }
    public AuthorResponseDto getAuthor(int authorId){
        Author author=authorRepository.findById(authorId).get();
        AuthorResponseDto authorResponseDto=new AuthorResponseDto();

        List<Book> bookList=author.getBooksWritten();
        List<BookResponseDto>booksWrittenDto=new ArrayList<>();

        for(Book b:bookList){
            BookResponseDto bookResponseDto=new BookResponseDto();
            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setName(b.getName());
            bookResponseDto.setPages(b.getPages());

            booksWrittenDto.add(bookResponseDto);
        }

        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setCountry(author.getCountry());
        authorResponseDto.setRating(author.getRating());
        authorResponseDto.setName(author.getName());

        authorResponseDto.setBooksWritten(booksWrittenDto);

        return authorResponseDto;
    }


}
