package com.project.parking.data.repository;

import com.project.parking.data.entity.Message;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
}
