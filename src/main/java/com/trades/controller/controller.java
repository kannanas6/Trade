package com.trades.controller;


import com.trades.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trades.model.Book;

import java.util.List;


@RestController
    @RequestMapping("/books")
    public class controller {

      /*  @GetMapping("/{id}")
        public ResponseEntity<String> getBookById(@PathVariable Long id) {
            // Logic to retrieve the book by id
            return ResponseEntity.ok( "Sample Book Title");
        }*/

        @Autowired
        BookService bookService;

        @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public Book getBookById(@PathVariable Long id) {
            // Logic to retrieve the book by id
            return bookService.getBookById(id);
        }

        @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
        public Book saveBook(@RequestBody Book book){
            return bookService.saveBook(book);
        }

        @GetMapping(value="getTit/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<Book> getBookByTitle(@PathVariable String title){
          return  bookService.getTitleContain(title);
        }
    }

