package com.project.parking.service;

import com.project.parking.dao.behavior.IVehicleDao;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;
import com.project.parking.service.behavior.IVehicleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {

    @NonNull
    private final IVehicleDao vehicleDao;

    @Override
    public PageDTO<List<VehicleDTO>> getAllObject(Map<String, Object> queryParams) {
        return vehicleDao.select(queryParams);
    }

    @Override
    public Optional<VehicleDTO> getObjectById(Long id) {
        return vehicleDao.select(id);
    }

    @Override
    public Optional<VehicleDTO> updateObjectById(Long id, VehicleDTO object) {
        return vehicleDao.update(id,object);
    }

    @Override
    public Optional<VehicleDTO> deleteObjectById(Long id) {
        return vehicleDao.delete(id);
    }

    @Override
    public Optional<VehicleDTO> insertObject(VehicleDTO object) {
        return vehicleDao.insert(object);
    }

    @Override
    public PageDTO<List<VehicleDTO>> getAllVehicleByUserList(Map<String, Object> queryParams) {
        return vehicleDao.getAllVehicleByUserList(queryParams);
    }
}
