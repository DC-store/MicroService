package com.MicroService.Rating.Dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse {

    public String msg;

    public boolean sucess;

    public HttpStatus status;


}
