package com.HotelService.MicroService.ResponseCapture;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse {

   private String msg;

  private  boolean sucess;


   private HttpStatus status;
}
