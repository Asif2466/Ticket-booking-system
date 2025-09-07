package com.example.asif.inventoryService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venue")
public class Venue {

    @Id
    private long id;
    private String name;
    private String address;
    private Long totalCapacity;

    // Additional fields and methods can be added as needed
}
