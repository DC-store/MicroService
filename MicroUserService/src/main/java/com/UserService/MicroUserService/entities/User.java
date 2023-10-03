package com.UserService.MicroUserService.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="user_micro")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name="user_name")
    private String name;

    @Column(name="user_email")
    private String email;


    @Column(name="user_info")
    private String about;

    @Transient
    private List<RatingEntity> ratingList = new ArrayList<>();
}
