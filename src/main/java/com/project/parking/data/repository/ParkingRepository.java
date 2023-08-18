package com.project.parking.data.repository;

import com.project.parking.data.entity.Parking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ParkingRepository extends PagingAndSortingRepository<Parking, Long> {

    @Query("SELECT pa FROM Parking pa " +
            "WHERE pa.name =:search " +
            "AND pa.owner.id =:owner")
    Page<Parking> findAllByNameAndOwner(@Param("search") String search,@Param("owner") Long owner, Pageable pageable);

    @Query("SELECT pa FROM Parking pa " +
            "WHERE pa.owner.id =:owner")
    Page<Parking> findAllByOwner(@Param("owner") Long owner, Pageable pageable);

    static final String HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(p.latitude)) * cos(radians(p.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(p.latitude))))";
    @Query("SELECT p FROM Parking p ORDER BY "+HAVERSINE_PART+" ASC")
    Page<Parking> findParkingWithInDistance(@Param("latitude") double latitude, @Param("longitude") double longitude, Pageable pageable);
}
