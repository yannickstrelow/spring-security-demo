package de.sninvent.springsecuritydemo.service;

import de.sninvent.springsecuritydemo.entity.CustomUser;
import de.sninvent.springsecuritydemo.entity.Permission;
import de.sninvent.springsecuritydemo.entity.Role;
import de.sninvent.springsecuritydemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DaoUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUser user = repository.findByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Username not found.");
        }

        List<SimpleGrantedAuthority> userRoles = user.getRoles()
                .stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .toList();
        List<GrantedAuthority> authorities = new ArrayList<>(userRoles);

        List<SimpleGrantedAuthority> userPermissions = user.getRoles()
                .stream()
                .map(Role::getPermissions)
                .map(ArrayList::new)
                .flatMap(List::stream)
                .map(Permission::getName)
                .map(SimpleGrantedAuthority::new)
                .toList();
        authorities.addAll(userPermissions);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
