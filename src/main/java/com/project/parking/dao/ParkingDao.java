package com.project.parking.dao;

import com.project.parking.dao.behavior.IParkingDao;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ParkingDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.Parking;
import com.project.parking.data.entity.User;
import com.project.parking.data.model.DefaultsParamsModel;
import com.project.parking.data.repository.ParkingRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingDao implements IParkingDao {

    @NonNull
    private final ParkingRepository parkingRepository;

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
        return Optional.empty();
    }

    @Override
    public Optional<ParkingDTO> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ParkingDTO> insert(ParkingDTO object) {
        return Optional.empty();
    }
}
