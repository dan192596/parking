package com.project.parking.dao;

import com.project.parking.dao.behavior.IVehicleDao;
import com.project.parking.data.defaults.StatusDefault;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.dto.VehicleDTO;
import com.project.parking.data.entity.User;
import com.project.parking.data.entity.Vehicle;
import com.project.parking.data.model.DefaultsParamsModel;
import com.project.parking.data.repository.UserRepository;
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

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleDao implements IVehicleDao {

    @NonNull
    private final VehicleRepository vehicleRepository;

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final StatusDefault statusDefault;

    @Override
    public Optional<VehicleDTO> select(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(VehicleDTO::new);
    }

    @Override
    public PageDTO<List<VehicleDTO>> select(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<Vehicle> vehicles = vehicleRepository.findAll(pageable);

        return new PageDTO<>(
                vehicles.getContent()
                        .stream()
                        .map(VehicleDTO::new)
                        .collect(Collectors.toList()),
                vehicles.getTotalElements(),
                params.getIndex(),
                vehicles.getContent().size()
        );
    }

    @Override
    public Optional<VehicleDTO> update(Long id, VehicleDTO object) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        vehicleOptional.ifPresent(vehicle -> {
            vehicle.setPlate(object.getPlate());
            vehicle.setColor(object.getColor());
            vehicle.setBrand(object.getBrand());
            vehicle.setLine(object.getLine());
            vehicle.setModel(object.getModel());
            vehicleRepository.save(vehicle);
        });
        return vehicleOptional.map(VehicleDTO::new);
    }

    @Override
    public Optional<VehicleDTO> delete(Long id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        vehicleOptional.ifPresent(vehicle -> {
            //Cambiar stattus a deshabilitado
        });
        return Optional.empty();
    }

    @Override
    public Optional<VehicleDTO> insert(VehicleDTO object) {
        Optional<User> userOptional = userRepository.findById(object.getUser());

        if(!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setPlate(object.getPlate());
        vehicle.setColor(object.getColor());
        vehicle.setBrand(object.getBrand());
        vehicle.setLine(object.getLine());
        vehicle.setModel(object.getModel());
        vehicle.setUser(userOptional.get());
        vehicle.setStatus(statusDefault.getEnabled());
        return Optional.of(new VehicleDTO(vehicleRepository.save(vehicle))) ;
    }

    @Override
    public PageDTO<List<VehicleDTO>> getAllVehicleByUserList(Map<String, Object> queryParams) {
        System.out.println(queryParams.containsKey("user"));
        System.out.println((String) queryParams.get("user"));
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        System.out.println(params.getUser());
        Page<Vehicle> vehicles = vehicleRepository.findAllByUserId(params.getUser(), pageable);

        return new PageDTO<>(
                vehicles.getContent()
                        .stream()
                        .map(VehicleDTO::new)
                        .collect(Collectors.toList()),
                vehicles.getTotalElements(),
                params.getIndex(),
                vehicles.getContent().size()
        );
    }
}
