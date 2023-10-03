package com.UserService.MicroUserService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RatingEntity {

    private long ratingId;

    @JsonIgnore
    private long userId;

    private long hotelId;

    private int rating;

    private String feedBack;

    private Hotel hotelObj;

}
