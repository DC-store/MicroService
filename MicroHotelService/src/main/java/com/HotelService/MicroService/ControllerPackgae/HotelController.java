package com.HotelService.MicroService.ControllerPackgae;

import com.HotelService.MicroService.Entities.Hotel;
import com.HotelService.MicroService.ServiceLayer.hotelService;
import jakarta.persistence.NamedStoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {


    @Autowired
    private hotelService service;


    @PostMapping("/createHotel")
    public ResponseEntity<Object> creatingHotel(@RequestBody Hotel hotel){


        Hotel hot= service.createHotel(hotel);

        return new ResponseEntity<>(hot, HttpStatus.CREATED);

    }

    @GetMapping("/findAllHotel")
    public ResponseEntity<Object> findAll(){
        List<Hotel> hotelList = service.getAllHotel();

        return new ResponseEntity<>((List<Hotel>) hotelList ,HttpStatus.FOUND);
    }


    @GetMapping("/findOne/{hotelId}")
    public ResponseEntity<Object> findOne(@PathVariable Long hotelId){
        Hotel hotel= service.findOneHotel(hotelId);

        return new ResponseEntity<>((Hotel)hotel, HttpStatus.FOUND);
    }



    @DeleteMapping("/deletById/{hotelId}")
    public ResponseEntity<Object> deletone(@PathVariable Long hotelId){

        service.deletHotel(hotelId);

        return new ResponseEntity<>("hotel deleted Sucess fully ", HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/updateHotel/{HotelId}")
    public ResponseEntity<Object> updateHotel(@PathVariable("HotelId") Long HotelId, @RequestBody Hotel hotel){
        
      Hotel hotelUpdated =   service.updateHotelData(HotelId,hotel);
      
      return new ResponseEntity<>(hotelUpdated,HttpStatus.FOUND);

         
    }
        



}
