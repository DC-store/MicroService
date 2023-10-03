package com.MicroService.Rating.repository;

import com.MicroService.Rating.entities.Rating;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {


    public List<Rating> findByUserId(Long userId);

    public List<Rating> findByHotelId(Long hotelId);


}
