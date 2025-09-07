package com.example.asif.bookingservice.repository;

import com.example.asif.bookingservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Define custom query methods if needed
    // For example, find customers by name, email, etc.
}
