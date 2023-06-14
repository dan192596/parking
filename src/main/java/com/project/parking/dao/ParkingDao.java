package com.project.parking.dao;

import com.project.parking.dao.behavior.IParkingDao;
import com.project.parking.data.defaults.StatusDefault;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ParkingDTO;
import com.project.parking.data.dto.ReservationDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.Parking;
import com.project.parking.data.entity.Reservation;
import com.project.parking.data.entity.User;
import com.project.parking.data.entity.Vehicle;
import com.project.parking.data.model.DefaultsParamsModel;
import com.project.parking.data.repository.ParkingRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingDao implements IParkingDao {

    @NonNull
    private final ParkingRepository parkingRepository;

    @NonNull
    private final StatusDefault statusDefault;

    @Override
    public Optional<ParkingDTO> select(Long id) {
        Optional<Parking> parking = parkingRepository.findById(id);
        return parking.map(ParkingDTO::new);
    }

    @Override
    public PageDTO<List<ParkingDTO>> select(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<Parking> parkings = parkingRepository.findAll(pageable);

        return new PageDTO<>(
                parkings.getContent()
                        .stream()
                        .map(ParkingDTO::new)
                        .collect(Collectors.toList()),
                parkings.getTotalElements(),
                params.getIndex(),
                parkings.getContent().size()
        );
    }

    @Override
    public Optional<ParkingDTO> update(Long id, ParkingDTO object) {
        Optional<Parking> reservationOptional = parkingRepository.findById(id);
        reservationOptional.ifPresent(parking -> {
            parking.setLocation(object.getLocation());
            parking.setLatitude(object.getLatitude());
            parking.setLongitude(object.getLongitude());
            parking.setPrice_hour(object.getPriceHour());
            parking.setName(object.getName());
            parkingRepository.save(parking);
        });
        return reservationOptional.map(ParkingDTO::new);
    }

    @Override
    public Optional<ParkingDTO> delete(Long id) {
        Optional<Parking> parkingOptional = parkingRepository.findById(id);
        if(!parkingOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parqueo inexistente");
        }
        Parking parking = parkingOptional.get();
        parking.setStatus(statusDefault.getDisabled());
        return Optional.of(new ParkingDTO(parkingRepository.save(parking)));
    }

    @Override
    public Optional<ParkingDTO> insert(ParkingDTO object) {
        Parking parking = new Parking();
        parking.setLocation(object.getLocation());
        parking.setLatitude(object.getLatitude());
        parking.setLongitude(object.getLongitude());
        parking.setPrice_hour(object.getPriceHour());
        parking.setStatus(statusDefault.getEnabled());
        parking.setName(object.getName());
        return Optional.of(new ParkingDTO(parkingRepository.save(parking)));
    }
}
