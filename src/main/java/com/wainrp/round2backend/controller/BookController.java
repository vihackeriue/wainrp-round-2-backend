package com.wainrp.round2backend.controller;

import com.wainrp.round2backend.dto.request.BookRequest;
import com.wainrp.round2backend.dto.response.ApiResponse;
import com.wainrp.round2backend.dto.response.BookResponse;
import com.wainrp.round2backend.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {

    BookService bookService;

    @PostMapping
    public ApiResponse<BookResponse> create(@RequestBody BookRequest request) {
        return ApiResponse.<BookResponse>builder()
                .data(bookService.save(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BookResponse>> getAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {

        if (page != null && limit != null) {
            Pageable pageable = PageRequest.of(page - 1, limit);

            return ApiResponse.<List<BookResponse>>builder()
                    .page(page)
                    .totalPages((int) Math.ceil((double) bookService.totalItem() / limit))
                    .data(bookService.findAll(pageable))
                    .build();
        }

        return ApiResponse.<List<BookResponse>>builder()
                .data(bookService.findAll())
                .build();
    }
}
