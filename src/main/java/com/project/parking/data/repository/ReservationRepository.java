package com.project.parking.data.repository;

import com.project.parking.data.entity.Parking;
import com.project.parking.data.entity.Reservation;
import com.project.parking.data.entity.Status;
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

    @Query("SELECT re FROM Reservation  re " +
            "WHERE re.parking.owner.id = :userId ")
    Page<Reservation> findAllByOwnerId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT re FROM Reservation  re " +
            "WHERE re.parking.owner.id = :userId " +
            "AND re.vehicle.plate =:vehicle")
    Page<Reservation> findAllByOwnerIdAndVehicle(@Param("userId") Long userId, @Param("vehicle") String vehicle, Pageable pageable);

    static final String HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(re.parking.latitude)) * cos(radians(re.parking.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(re.parking.latitude))))";
    @Query("SELECT re FROM Reservation re " +
            "WHERE " +
//            "re.status = :status " +
            " re.vehicle.user.id =:user " +
            "AND re.startDatetime > :start " +
            "AND re.endDatetime < :end " +
            "ORDER BY "+HAVERSINE_PART+" ASC")
    Page<Reservation> findReservationWithInDistance(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
//            @Param("status") Status status,
//            @Param("status2") Status status2,
            @Param("user") Long user,
            @Param("start") Date start,
            @Param("end") Date end,
            Pageable pageable
    );

}
