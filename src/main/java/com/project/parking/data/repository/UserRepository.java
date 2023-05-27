package com.project.parking.data.repository;

import com.project.parking.data.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByIdentifier(String identifier);
}
