package com.project.parking.data.repository;

import com.project.parking.data.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

    @Query("SELECT re FROM Reservation  re " +
            "WHERE re.startDatetime < :dateReservation " +
            "AND re.endDatetime > :dateReservation ")
    List<Reservation> findAllByDatetime(@Param("dateReservation") Date dateReservation);

    @Query("SELECT re FROM Reservation  re " +
            "WHERE (re.startDatetime < :dateReservation " +
            "AND re.endDatetime > :dateReservation) " +
            "AND re.vehicle.id = :vehicleId")
    List<Reservation> findAllByDatetimeAndVehicleId(@Param("dateReservation") Date dateReservation, @Param("vehicleId") Long vehicleId);

    @Query("SELECT re FROM Reservation  re " +
            "WHERE re.vehicle.user.id = :userId " +
            "AND re.status.id = :status")
    Page<Reservation> findAllByStatusAndUserId(@Param("userId") Long userId, @Param("status") Long status, Pageable pageable);

    @Query("SELECT re FROM Reservation  re " +
            "WHERE re.vehicle.user.id = :userId ")
    Page<Reservation> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

}
