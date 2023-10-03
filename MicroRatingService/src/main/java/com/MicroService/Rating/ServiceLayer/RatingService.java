package com.MicroService.Rating.ServiceLayer;

import com.MicroService.Rating.Exceptions.ResourceNotFoundException;
import com.MicroService.Rating.entities.Rating;
import com.MicroService.Rating.repository.RatingRepository;
import com.MicroService.Rating.serviceInterface.RatingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService implements RatingInterface {

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {


        Rating newrating= new Rating();

        newrating.setFeedback(rating.getFeedback());
        newrating.setRating(rating.getRating());
        newrating.setHotelId(rating.getHotelId());
        newrating.setUserId(rating.getUserId());

       newrating= ratingRepository.save(newrating);

        return newrating;
    }

    @Override
    public List<Rating> findAll() {

      List<Rating> ratingList=  ratingRepository.findAll();


        return ratingList;
    }

    @Override
    public Rating findOneRating(Long ratingId) {

        Rating singleRatingId = ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("there are no rating for this particular id "+ratingId));




        return singleRatingId;
    }

    @Override
    public String deletRating(Long ratingId) {

        ratingRepository.deleteById(ratingId);

        return "Sucessfully deleted";


    }

    @Override
    public Rating updateRating(Rating rating, Long ratingId) {

        Rating singleRatingId = ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("there are no rating for this particular id "+ratingId));

        if(singleRatingId.getUserId()!= rating.getUserId()){
            singleRatingId.setUserId(rating.getUserId());

        }
        if(singleRatingId.getRating()!= rating.getRating()){
            singleRatingId.setRating(rating.getRating());

        }

        if(!singleRatingId.getFeedback().equals(rating.getFeedback())){
            singleRatingId.setFeedback(rating.getFeedback());
        }
        if(singleRatingId.getHotelId()!= rating.getHotelId()){

            singleRatingId.setRating(rating.getRating());
        }

        singleRatingId =ratingRepository.save(singleRatingId);
        return singleRatingId;
    }
}
