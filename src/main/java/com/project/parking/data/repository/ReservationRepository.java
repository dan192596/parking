package com.project.parking.data.repository;

import com.project.parking.data.entity.Reservation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {
}
