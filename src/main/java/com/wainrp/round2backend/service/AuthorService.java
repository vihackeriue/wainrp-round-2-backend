package com.wainrp.round2backend.service;

import com.wainrp.round2backend.dto.request.AuthorRequest;
import com.wainrp.round2backend.dto.response.AuthorResponse;
import com.wainrp.round2backend.entity.Author;
import com.wainrp.round2backend.mapper.AuthorMapper;
import com.wainrp.round2backend.repository.AuthorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class AuthorService {
    AuthorRepository authorRepository;
    AuthorMapper authorMapper;

    public AuthorResponse save(AuthorRequest request) {
        Author author = authorMapper.toAuthor(request);
        author = authorRepository.save(author);
        return authorMapper.toAuthorResponse(author);
    }
    public List<AuthorResponse> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toAuthorResponse)
                .toList();
    }

    public AuthorResponse update(Long id, AuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // update field
        author.setName(request.getName());

        author = authorRepository.save(author);

        return authorMapper.toAuthorResponse(author);
    }
    public void delete(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        authorRepository.delete(author);
    }

    public List<AuthorResponse> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(authorMapper::toAuthorResponse)
                .toList();
    }

    public long totalItem() {
        return authorRepository.count();
    }

}
