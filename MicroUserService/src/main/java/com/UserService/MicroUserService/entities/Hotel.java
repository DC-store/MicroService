package com.UserService.MicroUserService.entities;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel {


    private Long hotelId;

    private String hotelName;

    private String hotelLocation;

    private String about;
}
