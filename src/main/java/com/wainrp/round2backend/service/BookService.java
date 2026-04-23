package com.wainrp.round2backend.service;

import com.wainrp.round2backend.dto.request.BookRequest;
import com.wainrp.round2backend.dto.response.BookResponse;
import com.wainrp.round2backend.entity.Author;
import com.wainrp.round2backend.entity.Book;
import com.wainrp.round2backend.mapper.BookMapper;
import com.wainrp.round2backend.repository.AuthorRepository;
import com.wainrp.round2backend.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class BookService {
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    BookMapper bookMapper;
    //Tạo Book
    public BookResponse save(BookRequest request) {

        Book book = bookMapper.toBook(request);

        // set Author
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        book.setAuthor(author);

        book = bookRepository.save(book);

        return bookMapper.toBookResponse(book);
    }

    public List<BookResponse> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    public List<BookResponse> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }
    public long totalItem() {
        return bookRepository.count();
    }



}
