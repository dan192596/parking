package com.project.parking.data.repository;

import com.project.parking.data.entity.Vehicle;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VehicleRepository extends PagingAndSortingRepository <Vehicle, Long> {
}
