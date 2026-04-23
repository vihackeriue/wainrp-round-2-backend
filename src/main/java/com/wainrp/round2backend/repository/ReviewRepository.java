package com.wainrp.round2backend.repository;

import com.wainrp.round2backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
