package de.sninvent.springsecuritydemo.controller;

import de.sninvent.springsecuritydemo.entity.Customer;
import de.sninvent.springsecuritydemo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/customers/getAll")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(service.getAll());
    }
}
