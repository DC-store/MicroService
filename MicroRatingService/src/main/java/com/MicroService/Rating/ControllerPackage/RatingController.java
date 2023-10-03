package com.MicroService.Rating.ControllerPackage;

import com.MicroService.Rating.ServiceLayer.RatingService;
import com.MicroService.Rating.entities.Rating;
import com.MicroService.Rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.init.ResourceReaderRepositoryPopulator;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {


    @Autowired
    private RatingService service;


    @Autowired
    private RatingRepository ratingrepo;
    @PostMapping("/createRating")
    public ResponseEntity<Object> createRating(@RequestBody Rating rating){

       Rating creatiedRating= service.createRating(rating);


       return new ResponseEntity<>((Rating) creatiedRating , HttpStatus.CREATED);

    }



    @GetMapping("/findOne/{ratingId}")
    public ResponseEntity<Object> findRating(@PathVariable Long ratingId){
        Rating rating = service.findOneRating(ratingId);
        return new ResponseEntity<>((Rating) rating, HttpStatus.FOUND);
    }


    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll(){


        List<Rating> ratingList= service.findAll();

        return new ResponseEntity<>((List<Rating>)ratingList,HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteRating")
    public ResponseEntity<Object> deleteRating(@PathVariable Long ratingId){


        service.deletRating(ratingId);
        return new ResponseEntity<>("the Rating has been deleted",HttpStatus.ACCEPTED);
    }



    @PutMapping("/updateRating/{ratingId}")
    public ResponseEntity<Object> updateRating(@PathVariable Long ratingId, @RequestBody Rating rating){

        Rating ratingUpdated=service.updateRating(rating,ratingId);

        return new ResponseEntity<>((Rating) ratingUpdated , HttpStatus.ACCEPTED);

    }

    @GetMapping("/findByUserId/{userId}")
    public ResponseEntity<Object> getRatingByUserId(@PathVariable Long userId){

        List<Rating> rating= ratingrepo.findByUserId(userId);
        System.out.println("rating is null or not null:::"+rating);

        List<Rating> newRatingObj = new ArrayList<>();
        for (Rating ratingObj:rating) {
            Rating r = Rating.builder().ratingId(ratingObj.
                    getRatingId()).userId(ratingObj.getUserId()).
                    rating(ratingObj.getRating()).hotelId(ratingObj.getHotelId()).feedback(ratingObj.getFeedback()).build();

            newRatingObj.add(r);
        }


        if (newRatingObj!=null) {
            return ResponseEntity.ok(newRatingObj);
        } else {
            return ResponseEntity.notFound().build();
        }




    }


    @GetMapping("/findByHotelId/{ratingId}")
    public ResponseEntity<Object> getRatingByHotelId(@PathVariable("ratingIdzzzz") Long hotelId){

        List<Rating> ratingByHotelId= ratingrepo.findByHotelId(hotelId);

        return new ResponseEntity<>((List<Rating>)ratingByHotelId,HttpStatus.FOUND);

    }



}

