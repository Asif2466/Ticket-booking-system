package com.example.asif.inventoryService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class Event {
    @Id
    private long id;
    private String name;
    private Long totalCapacity;
    private Long availableCapacity;
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
    private BigDecimal ticketPrice;
}
