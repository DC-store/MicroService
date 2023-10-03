package com.UserService.MicroUserService.ControllerPackage;

import com.UserService.MicroUserService.Exceptions.ResourceNotFoundException;
import com.UserService.MicroUserService.ServiceLayer.UserService;
import com.UserService.MicroUserService.entities.User;
import com.UserService.MicroUserService.repo.UserRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserServiceController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    private Logger logger = LoggerFactory.getLogger(UserServiceController.class);

    @PostMapping("/createUser")
    public ResponseEntity<User> creatUser(@RequestBody User user){

        System.out.println("creating the new user ");

       User usercreated = userService.createUser(user);
        return new ResponseEntity<>(usercreated, HttpStatus.CREATED);
    }


    @GetMapping("/findAllUsers")
    public ResponseEntity<Object> getAllUsers(){

        List<User> allUsers = userService.findAll();

        if(allUsers.isEmpty()){

         return new ResponseEntity<>("the data is not present in the data Base ",HttpStatus.NOT_FOUND);
        }

        else{
            return new ResponseEntity<>((List<User>)allUsers,HttpStatus.FOUND);
        }


    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Long userId){


        userService.deleteUser(userId);

        return new ResponseEntity<>("deleted Sucessfully", HttpStatus.ACCEPTED);


    }


    @PutMapping("/updatingUser/{userID}")
    public ResponseEntity<Object> updateUser(@PathVariable("userID") Long userId,@RequestBody User user){

        User userupdated =userService.updateUser(userId,user);

        return new ResponseEntity<>(userupdated,HttpStatus.ACCEPTED);

    }
int retryCount=1;
    @GetMapping("/userFinding")
   // @CircuitBreaker(name="user-rating-hotel-service" ,fallbackMethod = "circuitBreakerForFindByUserId")
    @Retry(name="RatingHotelService",fallbackMethod = "circuitBreakerForFindByUserId")
   // @RateLimiter(name="RatingHotelServiceLimitter",fallbackMethod = "circuitBreakerForFindByUserId")
    //here we can set the limit and make it to limit the Retry or maximum number of request that can be triggered for an api
    public ResponseEntity<Object> findBasedOnId(@RequestParam("userId") Long userId){
        logger.info("finding by user ID");
        logger.info("retry count "+retryCount);
        retryCount++;

        User userBasedOnId= userService.findById(userId);

        if (userBasedOnId!=null) {
            return ResponseEntity.ok(userBasedOnId);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    public ResponseEntity<Object>  circuitBreakerForFindByUserId(Long userId,Exception excpetion){

        StringBuffer buffer = new StringBuffer();
        buffer.append("there is a problem with one of the server please check it the reason is:: "+excpetion.getMessage());

        return ResponseEntity.ok(buffer);
    }

    @GetMapping("/userByQuery/{userId}")
    public ResponseEntity<User> findUserByQuer(@PathVariable("userId") Long userId){

        User user= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("No Data Found for this Id "+userId));

        return new ResponseEntity<>(user,HttpStatus.FOUND) ;

    }




}
