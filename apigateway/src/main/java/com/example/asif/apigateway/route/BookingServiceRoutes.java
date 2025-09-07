package com.example.asif.apigateway.route;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

@Configuration
public class BookingServiceRoutes {

    @Bean
    public RouterFunction<ServerResponse> bookingRoutes(){
        return GatewayRouterFunctions.route("booking-service")
                .route(RequestPredicates.POST("/api/v1/booking"),
                        HandlerFunctions.http("http://localhost:8081/api/v1/booking"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("bookingServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute/bookingService")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoutes(){
        return GatewayRouterFunctions.route("fallback-route")
                .POST("/fallbackRoute/bookingService",
                request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Booking Service is down, please try again later."))
                .build();
    }

}
