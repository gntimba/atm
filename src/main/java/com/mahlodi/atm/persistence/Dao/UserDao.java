package com.mahlodi.atm.persistence.Dao;

import com.mahlodi.atm.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface UserDao extends JpaRepository<User, Long> {
 Optional<User> findByEmail(String username);
}
