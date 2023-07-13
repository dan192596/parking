package com.project.parking.data.repository;

import com.project.parking.data.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

    @Query("SELECT re FROM Reservation  re " +
            "WHERE re.startDatetime < :dateReservation OR re.endDatetime > :dateReservation ")
    List<Reservation> findAllByDatetime(@Param("dateReservation") Date dateReservation);

}
