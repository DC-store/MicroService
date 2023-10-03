package com.HotelService.MicroService.ServiceInterface;

import com.HotelService.MicroService.Entities.Hotel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public interface HotelServiceInter {

    public Hotel createHotel(Hotel hotel);

    public List<Hotel> getAllHotel();

    public Hotel updateHotelData(Long hotelId,Hotel hotel);

    public void deletHotel(Long hotelId);

    public Hotel findOneHotel(Long hotelId);


}
