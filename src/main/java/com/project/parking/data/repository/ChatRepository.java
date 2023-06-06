package com.project.parking.data.repository;

import com.project.parking.data.entity.Chat;
import com.project.parking.data.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChatRepository extends PagingAndSortingRepository<Chat, Long> {
    Page<Chat> findAllByUserId(Long userId, Pageable pageable);
}
