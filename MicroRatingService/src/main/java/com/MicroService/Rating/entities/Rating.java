package com.MicroService.Rating.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="micro_rating")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ratingId;

    private Long userId;

    private Long hotelId;

    private String feedback;

    private int rating;






}
