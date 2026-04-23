package com.wainrp.round2backend.service;

import com.wainrp.round2backend.dto.request.ReviewRequest;
import com.wainrp.round2backend.dto.response.ReviewResponse;

import com.wainrp.round2backend.entity.Book;
import com.wainrp.round2backend.entity.Review;
import com.wainrp.round2backend.mapper.ReviewMapper;
import com.wainrp.round2backend.repository.BookRepository;
import com.wainrp.round2backend.repository.ReviewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class ReviewService {

    ReviewRepository reviewRepository;
    ReviewMapper reviewMapper;
    BookRepository bookRepository;

    public ReviewResponse save(ReviewRequest request) {

        Review review = reviewMapper.toReview(request);

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        review.setBook(book);

        review = reviewRepository.save(review);

        return reviewMapper.toReviewResponse(review);
    }


    public List<ReviewResponse> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toReviewResponse)
                .toList();
    }
    public List<ReviewResponse> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(reviewMapper::toReviewResponse)
                .toList();
    }
    public long totalItem() {
        return reviewRepository.count();
    }

}
