package com.HotelService.MicroService.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="micro_hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelId;

    private String hotelName;

    private String hotelLocation;

    private String about;
}
