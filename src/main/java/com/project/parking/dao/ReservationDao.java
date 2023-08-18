package com.project.parking.dao;

import com.project.parking.dao.behavior.IReservationDao;
import com.project.parking.data.defaults.StatusDefault;
import com.project.parking.data.dto.*;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
            try {
                reservation.setStartDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(object.getStart()));
                reservation.setEndDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(object.getEnd()));
                reservationRepository.save(reservation);
            } catch (ParseException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha erroneo");
            }

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
        try {
            reservation.setStartDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(object.getStart()));
            reservation.setEndDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(object.getEnd()));
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha erroneo");
        }
        reservation.setParking(parkingOptional.get());
        reservation.setVehicle(vehicleOptional.get());
        reservation.setStatus(statusDefault.getPending());
        return Optional.of(new ReservationDTO(reservationRepository.save(reservation)));
    }

    @Override
    public PageDTO<List<ReservationDTO>> getAllReservationsByUserList(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<Reservation> reservations;
        if(params.getStatus()==null){
            reservations = reservationRepository.findAllByUserId(params.getUser(),  pageable);
        }else{
            reservations = reservationRepository.findAllByStatusAndUserId(params.getUser(), params.getStatus(),  pageable);
        }

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
    public PageDTO<List<ReservationDTO>> getReservationsByUserConsole(Map<String, Object> queryParams) {

        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<Reservation> reservations = reservationRepository.findAllByOwnerId(params.getOwner(), pageable);

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
    public PageDTO<List<ReservationDTO>> selectReservationByDistance(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());

        Page<Reservation> reservations = reservationRepository.findReservationWithInDistance(
                params.getLatitude(),
                params.getLongitude(),
                statusDefault.getPending(),
                params.getUser(),
                params.getStartDate(),
                params.getEndDate(),
                pageable
        );

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
}
