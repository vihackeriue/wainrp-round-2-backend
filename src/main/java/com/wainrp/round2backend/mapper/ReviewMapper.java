package com.wainrp.round2backend.mapper;

import com.wainrp.round2backend.dto.request.ReviewRequest;
import com.wainrp.round2backend.dto.response.ReviewResponse;
import com.wainrp.round2backend.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "book.title", target = "bookName")
    @Mapping(source = "book.author.name", target = "authorName")
    @Mapping(source = "review", target = "review")
    ReviewResponse toReviewResponse(Review review);

    Review toReview(ReviewRequest request);
}
