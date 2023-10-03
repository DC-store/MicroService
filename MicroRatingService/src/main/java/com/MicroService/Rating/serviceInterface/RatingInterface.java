package com.MicroService.Rating.serviceInterface;

import com.MicroService.Rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingInterface {



    public Rating createRating(Rating rating);

    public List<Rating> findAll();

    public Rating findOneRating(Long hotelId);

    public String deletRating(Long ratingId);

    public Rating updateRating(Rating rating,Long hotelId);



}
