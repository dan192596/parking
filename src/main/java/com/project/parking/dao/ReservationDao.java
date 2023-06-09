package com.project.parking.dao;

import com.project.parking.dao.behavior.IReservationDao;
import com.project.parking.data.defaults.StatusDefault;
import com.project.parking.data.dto.MessageDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ReservationDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.*;
import com.project.parking.data.model.DefaultsParamsModel;
import com.project.parking.data.repository.ParkingRepository;
import com.project.parking.data.repository.ReservationRepository;
import com.project.parking.data.repository.VehicleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationDao implements IReservationDao {

    @NonNull
    private final ReservationRepository reservationRepository;

    @NonNull
    private final ParkingRepository parkingRepository;

    @NonNull
    private final VehicleRepository vehicleRepository;

    @NonNull
    private final StatusDefault statusDefault;

    @Override
    public Optional<ReservationDTO> select(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.map(ReservationDTO::new);
    }

    @Override
    public PageDTO<List<ReservationDTO>> select(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<Reservation> reservations = reservationRepository.findAll(pageable);

        return new PageDTO<>(
                reservations.getContent()
                        .stream()
                        .map(ReservationDTO::new)
                        .collect(Collectors.toList()),
                reservations.getTotalElements(),
                params.getIndex(),
                reservations.getContent().size()
        );
    }

    @Override
    public Optional<ReservationDTO> update(Long id, ReservationDTO object) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        reservationOptional.ifPresent(reservation -> {
            reservation.setStartDatetime(object.getStart());
            reservation.setEndDatetime(object.getEnd());
            reservationRepository.save(reservation);
        });
        return reservationOptional.map(ReservationDTO::new);
    }

    @Override
    public Optional<ReservationDTO> delete(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if(!reservationOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Reservacion inexistente");
        }
        Reservation reservation = reservationOptional.get();
        reservation.setStatus(statusDefault.getDisabled());
        return Optional.of(new ReservationDTO(reservationRepository.save(reservation)));
    }

    @Override
    public Optional<ReservationDTO> insert(ReservationDTO object) {
        Optional<Parking> parkingOptional = parkingRepository.findById(object.getParking().getId());
        if(!parkingOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parqueo inexistente");
        }

        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(object.getVehicle().getId());
        if(!vehicleOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehiculo inexistente");
        }

        Reservation reservation = new Reservation();
        reservation.setStartDatetime(object.getStart());
        reservation.setEndDatetime(object.getEnd());
        reservation.setParking(parkingOptional.get());
        reservation.setVehicle(vehicleOptional.get());
        reservation.setStatus(statusDefault.getPending());
        return Optional.of(new ReservationDTO(reservationRepository.save(reservation)));
    }
}
