package com.example.asif.orderservice.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public void updateEventCapacity(Long eventId, Long ticketCount) {
        // Implement the logic to call the Inventory Service to update the inventory
        // For example, using RestTemplate or WebClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        String url = inventoryServiceUrl + "/event/" + eventId + "/capacity/" + ticketCount;
        restTemplate.patchForObject(url, null, Void.class);
        log.info("Event capacity updated successfully");
    }
}
