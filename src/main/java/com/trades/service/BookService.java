package com.trades.service;

import com.trades.model.Book;
import com.trades.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book getBookById(Long id){
        System.out.println("It is Working---->");
      Book b=  bookRepository.getById(id);
if(b==null){
    System.out.println("Book Value is null");
}else{
    System.out.println("Value is--------->"+b.getTitle());
}
        return b;
    }

    public Book saveBook(Book book){
      return  bookRepository.save(book);
    }

    public List<Book> getTitleContain(String title) {
    return bookRepository.findByTitleContaining1(title);
    }
}
