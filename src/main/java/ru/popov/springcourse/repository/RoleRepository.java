package ru.popov.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popov.springcourse.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
