package com.UserService.MicroUserService.ConfigurationPkg;

import com.UserService.MicroUserService.ExternalService.RatingService;
import feign.Client;
import feign.Feign;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.support.DefaultClientCodecConfigurer;
import org.springframework.web.client.RestTemplate;
import feign.FeignException;
import feign.Response;
import feign.Response.Body;

import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
public class RatingServiceConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }




}
class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 302) {
            // Handle the redirect manually by extracting the Location heade

            System.out.println("the response string is "+response.body().toString());
            System.out.println("==================================================");
            System.out.println("the response is "+response);



            List<String> locationHeaders = (List<String>) response.headers().get("location");

            System.out.println("locationHeaders:::::::::"+locationHeaders);

            if (locationHeaders != null && !locationHeaders.isEmpty()) {
                String location = locationHeaders.get(0);

                // Make a new request to the redirected URL
                RatingService myFeignClient = Feign.builder()
                        .target(RatingService.class, location);

                // You can now make the redirected request using your Feign client
                // For example, if you have a method called 'findRatingById' in 'MyFeignClient', you can call it here.

                // Note: You may need to wrap this in a try-catch block and handle any FeignExceptions.
            } else {
                System.out.println("location header is missing");

            }

        }
        return FeignException.errorStatus(methodKey, response);
    }
}
