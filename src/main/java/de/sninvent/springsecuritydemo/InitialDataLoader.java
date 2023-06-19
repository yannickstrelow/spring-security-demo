package de.sninvent.springsecuritydemo;


import de.sninvent.springsecuritydemo.entity.CustomUser;
import de.sninvent.springsecuritydemo.entity.Customer;
import de.sninvent.springsecuritydemo.entity.Permission;
import de.sninvent.springsecuritydemo.entity.Role;
import de.sninvent.springsecuritydemo.repository.CustomerRepository;
import de.sninvent.springsecuritydemo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitialDataLoader {

    private final CustomerRepository customerRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @PostConstruct
    public void init() {

        Permission readPermission = new Permission();
        readPermission.setName("PRIVILEGE_READ");

        Permission writePermission = new Permission();
        writePermission.setName("PRIVILEGE_WRITE");

        Permission viewUsersPermission = new Permission();
        viewUsersPermission.setName("PRIVILEGE_VIEW_ALL_USERS");

        List<Permission> adminPermissions = List.of(
                readPermission, writePermission, viewUsersPermission);
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleAdmin.setPermissions(adminPermissions);

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleUser.setPermissions(List.of(readPermission));

        Role roleEmployee = new Role();
        roleEmployee.setName("ROLE_EMPLOYEE");
        roleEmployee.setPermissions(List.of(readPermission, viewUsersPermission));

        CustomUser admin = new CustomUser();
        admin.setRoles(List.of(roleAdmin));
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("admin"));

        CustomUser user = new CustomUser();
        user.setRoles(List.of(roleUser));
        user.setUsername("user");
        user.setPassword(encoder.encode("user"));

        CustomUser employee = new CustomUser();
        employee.setRoles(List.of(roleEmployee));
        employee.setUsername("employee");
        employee.setPassword(encoder.encode("employee"));

        List<CustomUser> users = List.of(user, admin, employee);
        userRepository.saveAll(users);

        Customer adminInfo = new Customer();
        adminInfo.setName("admin");
        adminInfo.setEmail("admin@admin.com");
        customerRepository.save(adminInfo);

        Customer userInfo = new Customer();
        userInfo.setName("user");
        userInfo.setEmail("user@user.com");
        customerRepository.save(userInfo);

        Customer employeeInfo = new Customer();
        employeeInfo.setName("employee");
        employeeInfo.setEmail("employee@employee.com");
        customerRepository.save(employeeInfo);
    }

}
