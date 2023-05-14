package com.project.parking.data.repository;

import com.project.parking.data.entity.Status;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {
}
