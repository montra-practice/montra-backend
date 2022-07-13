package com.epam.dao;

import com.epam.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {
    User findOneByEmail(String username);
}
