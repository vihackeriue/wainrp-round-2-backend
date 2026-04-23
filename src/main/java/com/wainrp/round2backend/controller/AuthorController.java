package com.wainrp.round2backend.controller;

import com.wainrp.round2backend.dto.request.AuthorRequest;
import com.wainrp.round2backend.dto.response.ApiResponse;
import com.wainrp.round2backend.dto.response.AuthorResponse;
import com.wainrp.round2backend.entity.Author;
import com.wainrp.round2backend.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorController {
    AuthorService authorService;

    @PostMapping()
    public ApiResponse<AuthorResponse> create(@RequestBody AuthorRequest request) {
        return ApiResponse.<AuthorResponse>builder()
                .data(authorService.save(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<AuthorResponse> updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorRequest request
    ) {
        return ApiResponse.<AuthorResponse>builder()
                .data(authorService.update(id, request))
                .build();
    }
    @GetMapping()
    public ApiResponse<List<AuthorResponse>> getAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {

        if (page != null && limit != null) {
            Pageable pageable = PageRequest.of(page - 1, limit);

            return ApiResponse.<List<AuthorResponse>>builder()
                    .page(page)
                    .totalPages((int) Math.ceil((double) authorService.totalItem() / limit))
                    .data(authorService.findAll(pageable))
                    .build();
        }

        return ApiResponse.<List<AuthorResponse>>builder()
                .data(authorService.findAll())
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);

        return ApiResponse.<String>builder()
                .data("Deleted successfully")
                .build();
    }
}
