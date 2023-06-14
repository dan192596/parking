package com.project.parking.data.repository;

import com.project.parking.data.entity.Chat;
import com.project.parking.data.entity.Parking;
import com.project.parking.data.entity.User;
import com.project.parking.data.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ChatRepository extends PagingAndSortingRepository<Chat, Long> {
    Page<Chat> findAllByUserId(Long userId, Pageable pageable);

    Optional<Chat> findByParkingAndUser(Parking parking, User user);
}
