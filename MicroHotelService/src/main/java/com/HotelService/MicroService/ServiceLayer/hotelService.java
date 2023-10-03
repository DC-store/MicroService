package com.HotelService.MicroService.ServiceLayer;

import com.HotelService.MicroService.Entities.Hotel;
import com.HotelService.MicroService.Exceptions.ResourceNotFoundException;
import com.HotelService.MicroService.RepositoyInterfcae.Hotelrepo;
import com.HotelService.MicroService.ServiceInterface.HotelServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class hotelService implements HotelServiceInter{



    @Autowired
    private Hotelrepo hotelrepo;

    @Override
    public Hotel createHotel(Hotel hotel) {
        Hotel hotel1 = new Hotel();

        hotel1.setHotelName(hotel.getHotelName());
        hotel1.setHotelLocation(hotel.getHotelLocation());
        hotel1.setAbout(hotel.getAbout());

        hotelrepo.save(hotel1);


        return hotel1;
    }

    @Override
    public List<Hotel> getAllHotel() {

      List<Hotel> hotellist =   hotelrepo.findAll();



        return hotellist;
    }

    @Override
    public Hotel updateHotelData(Long hotelId, Hotel hotel) {

       Hotel updateHotel=   hotelrepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("there is no data for this Id"));

       if(!hotel.getHotelLocation().equals(updateHotel.getHotelLocation())){
           updateHotel.setHotelLocation(hotel.getHotelLocation());;
       }
       if(!hotel.getHotelName().equals(updateHotel.getHotelName())){
           updateHotel.setHotelName(hotel.getHotelName());
       }
       if(!hotel.getAbout().equals(updateHotel.getAbout())){
           updateHotel.setAbout(hotel.getAbout());
       }



       updateHotel =hotelrepo.save(updateHotel);

        return updateHotel;
    }

    @Override
    public void deletHotel(Long hotelId) {

        hotelrepo.deleteById(hotelId);

    }

    @Override
    public Hotel findOneHotel(Long hotelId) {

       Hotel hoteid= hotelrepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("The data not found for this hotel id "+hotelId));

        return hoteid;
    }
}
