package com.neto.curso.repository;

import com.neto.curso.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(name = "SELECT u FROM User WHERE u.userName = :userName")
    User findByUsername(@Param("userName") String userName);
}
