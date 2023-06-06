package com.project.parking.data.repository;

import com.project.parking.data.entity.Message;
import com.project.parking.data.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    Page<Message> findAllByChatId(Long userId, Pageable pageable);
}
