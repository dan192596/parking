package com.project.parking.data.repository;

import com.project.parking.data.entity.ReservationPayment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationPaymentRepository extends PagingAndSortingRepository<ReservationPayment, Long> {
}
