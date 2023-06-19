package de.sninvent.springsecuritydemo.repository;

import de.sninvent.springsecuritydemo.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {

    CustomUser findByUsername(String username);
}
