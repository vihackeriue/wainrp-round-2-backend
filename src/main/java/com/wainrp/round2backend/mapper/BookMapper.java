package com.wainrp.round2backend.mapper;

import com.wainrp.round2backend.dto.request.BookRequest;
import com.wainrp.round2backend.dto.response.BookResponse;
import com.wainrp.round2backend.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "title", target = "name")
    @Mapping(source = "author.name", target = "authorName")
    BookResponse toBookResponse(Book book);

    @Mapping(source = "name", target = "title")
    Book toBook(BookRequest request);

}
