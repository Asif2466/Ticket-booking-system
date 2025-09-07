package com.example.asif.bookingservice.client;

import com.example.asif.bookingservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryResponse getInventory(final Long eventId) {
        // Here you would typically use a RestTemplate or WebClient to make a call to the inventory service
        // For example:
        // String url = inventoryServiceUrl + "/inventory/" + eventId;
        // return restTemplate.getForObject(url, InventoryResponse.class);
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl + "/event/" + eventId, InventoryResponse.class);
    }

}
