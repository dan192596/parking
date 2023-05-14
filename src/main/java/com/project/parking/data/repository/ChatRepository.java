package com.project.parking.data.repository;

import com.project.parking.data.entity.Chat;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChatRepository extends PagingAndSortingRepository<Chat, Long> {
}
