package com.UserService.MicroUserService.ResponseCapture;

import lombok.*;
import org.springframework.http.HttpStatus;
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

   private  String msg;

   private boolean sucess;

   private HttpStatus status;
}
