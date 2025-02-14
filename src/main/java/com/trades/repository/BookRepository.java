package com.trades.repository;

import com.trades.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByPriceAndTitle(BigDecimal price, String title);

    List<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title% and b.id=1L")
    List<Book> findByTitleContaining1(@Param("title") String title);

 /*   @Query("Select * from Book b where b.title= :title")
    List<Book> getSpecicTitle(String title);
*/
  //  List<Book> findByPublishDateAfter(LocalDate date);
}
