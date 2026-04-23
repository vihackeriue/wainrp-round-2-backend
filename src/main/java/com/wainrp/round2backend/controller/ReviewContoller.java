package com.wainrp.round2backend.controller;

import com.wainrp.round2backend.dto.request.ReviewRequest;
import com.wainrp.round2backend.dto.response.ApiResponse;
import com.wainrp.round2backend.dto.response.ReviewResponse;
import com.wainrp.round2backend.service.ReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewContoller {

    ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResponse> create(@RequestBody ReviewRequest request) {
        return ApiResponse.<ReviewResponse>builder()
                .data(reviewService.save(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ReviewResponse>> getAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {

        if (page != null && limit != null) {
            Pageable pageable = PageRequest.of(page - 1, limit);

            return ApiResponse.<List<ReviewResponse>>builder()
                    .page(page)
                    .totalPages((int) Math.ceil((double) reviewService.totalItem() / limit))
                    .data(reviewService.findAll(pageable))
                    .build();
        }

        return ApiResponse.<List<ReviewResponse>>builder()
                .data(reviewService.findAll())
                .build();
    }
}
