package com.project.parking.data.repository;

import com.project.parking.data.entity.Parking;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ParkingRepository extends PagingAndSortingRepository<Parking, Long> {
}
