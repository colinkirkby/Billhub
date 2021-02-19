package net.java.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.java.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{   //User is the JPA Entity, Long is the type of primary key
    User findByEmail(String email);
}
