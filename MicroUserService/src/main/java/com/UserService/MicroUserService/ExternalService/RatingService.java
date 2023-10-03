package com.UserService.MicroUserService.ExternalService;

import com.UserService.MicroUserService.entities.RatingEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {


    @GetMapping("/rating/findByUserId/{ratingId}")
    public List<RatingEntity> findRatingById(@PathVariable("ratingId") Long ratingId);
}
