package com.wainrp.round2backend.repository;

import com.wainrp.round2backend.entity.Book;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
