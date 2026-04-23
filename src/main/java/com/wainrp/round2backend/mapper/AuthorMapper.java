package com.wainrp.round2backend.mapper;

import com.wainrp.round2backend.dto.request.AuthorRequest;
import com.wainrp.round2backend.dto.response.AuthorResponse;
import com.wainrp.round2backend.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(
            target = "booksCount",
            expression = "java(author.getBooks() == null ? 0L : (long) author.getBooks().size())"
    )
    AuthorResponse toAuthorResponse(Author author);

    // map request -> entity
    Author toAuthor(AuthorRequest request);
}
