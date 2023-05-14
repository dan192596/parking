package com.project.parking.data.repository;

import com.project.parking.data.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    boolean existsByEmail(String email);
}
