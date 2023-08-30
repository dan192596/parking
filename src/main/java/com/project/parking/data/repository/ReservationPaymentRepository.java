package com.project.parking.data.repository;

import com.project.parking.data.entity.Reservation;
import com.project.parking.data.entity.ReservationPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ReservationPaymentRepository extends PagingAndSortingRepository<ReservationPayment, Long> {

    @Query("SELECT re FROM ReservationPayment re " +
            "WHERE re.reservation.parking.owner.id = :userId ")
    Page<ReservationPayment> findAllByOwnerId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT re FROM ReservationPayment re " +
            "WHERE re.reservation.parking.owner.id = :userId " +
            "AND re.reservation.id = :search ")
    Page<ReservationPayment> findAllByOwnerIdAndSearch(@Param("userId") Long userId, @Param("search")Long search, Pageable pageable);
}
