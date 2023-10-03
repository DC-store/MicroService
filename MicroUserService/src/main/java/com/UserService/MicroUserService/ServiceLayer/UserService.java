package com.UserService.MicroUserService.ServiceLayer;

import com.UserService.MicroUserService.Exceptions.ResourceNotFoundException;
import com.UserService.MicroUserService.ExternalService.RatingService;
import com.UserService.MicroUserService.ServiceInterface.UserServiceInterface;
import com.UserService.MicroUserService.entities.Hotel;
import com.UserService.MicroUserService.entities.RatingEntity;
import com.UserService.MicroUserService.entities.User;
import com.UserService.MicroUserService.repo.UserRepo;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.jar.JarOutputStream;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RatingService ratingServiceFeign;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);



    //private  String ratingService="http://localhost:9092/rating/findByUserId/";
    private  String ratingService="http://RATING-SERVICE/rating/findByUserId/";

    private String hotelService="http://HOTEL-SERVICE/hotel/findOne/";


    @Override
    public User createUser(User user) {

        User userobj = new User();

        userobj.setAbout(user.getAbout());
        userobj.setName(user.getName());
        userobj.setEmail(user.getEmail());
       User userob= userRepo.save(userobj);

        return userob;
    }

    @Override
    public List<User> findAll() {
        List<User> allUsers= userRepo.findAll();


        List<User> fullDataUsers = new ArrayList<>();
        for (User eachUser: allUsers) {
            String api = ratingService+eachUser.getUserId();

            List<RatingEntity> ratingEntity = restTemplate.getForObject(api, ArrayList.class);
            eachUser.setRatingList(ratingEntity);
            fullDataUsers.add(eachUser);

        }
        logger.info("{}", fullDataUsers);

        return fullDataUsers;
    }

    @Override
    public User findById(Long userId) {

        System.out.println("user ID fetching ");

       User user= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("No Data Found for this Id "+userId));

       String ratingServiceApi=ratingService+user.getUserId();
        System.out.println("rating service is some where going wrong === "+ratingServiceApi);


        List<RatingEntity> ratingEntityList = ratingServiceFeign.findRatingById(user.getUserId());

        System.out.println("rating entity feign client is success ::::: "+ratingEntityList);


       RatingEntity[] ratingEn = restTemplate.getForObject(ratingServiceApi, RatingEntity[].class);
        logger.info("{}",ratingEn);

        System.out.println("the rating by all the arrays is "+ratingEn);

        List<RatingEntity> ratingEntity =Arrays.stream(ratingEn).toList();


    List<RatingEntity> fulfilledRatingEntity = new ArrayList<>();
        for (RatingEntity ratings:ratingEntity) {

            System.out.println("*******************"+ratings.getFeedBack());

            String hotelServiceApi= hotelService+ratings.getHotelId();

            Hotel hotelEntity= restTemplate.getForObject(hotelServiceApi,Hotel.class);
            System.out.println("++++++++++++++++++++++++++++++++");
            ratings.setHotelObj(hotelEntity);
            fulfilledRatingEntity.add(ratings);

            logger.info("{} ",fulfilledRatingEntity);
        }


        user.setRatingList(fulfilledRatingEntity);



        return user;
    }

    @Override
    public void deleteUser(Long userId) {

        userRepo.deleteById(userId);
    }

    @Override
    public User updateUser(Long userId,User user) {

        User updatinguser= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("No Data Found for this Id "+userId));

        if(!user.getEmail().equals(updatinguser.getEmail())){
            updatinguser.setEmail(user.getEmail());
        }
        if(!user.getAbout().equals(updatinguser.getAbout())){
            updatinguser.setAbout(user.getAbout());
        }
        if(!user.getName().equals(updatinguser.getName())){
            updatinguser.setName(user.getName());
        }

        if(updatinguser!=null ||  updatinguser.toString().equals("")){
          updatinguser=  userRepo.save(updatinguser);
        }

        return updatinguser;
    }
}
