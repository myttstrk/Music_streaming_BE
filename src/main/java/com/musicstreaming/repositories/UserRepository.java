package com.musicstreaming.repositories;

import com.musicstreaming.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Page<User> findByUsernameContainingIgnoreCase(String keyword, Pageable pageable);

    Optional<User> findByPhoneNumber(String phoneNumber);


}
