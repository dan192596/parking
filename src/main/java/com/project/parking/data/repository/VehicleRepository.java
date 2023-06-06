package com.project.parking.data.repository;

import com.project.parking.data.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VehicleRepository extends PagingAndSortingRepository <Vehicle, Long> {

    Page<Vehicle> findAllByUserId(Long userId, Pageable pageable);
}
