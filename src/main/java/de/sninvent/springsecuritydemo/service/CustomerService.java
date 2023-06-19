package de.sninvent.springsecuritydemo.service;

import de.sninvent.springsecuritydemo.entity.Customer;
import de.sninvent.springsecuritydemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<Customer> getAll() {
        return repository.findAll();
    }

}
