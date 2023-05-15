package com.project.parking.service;

import com.project.parking.dao.behavior.IParkingDao;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ParkingDTO;
import com.project.parking.service.behavior.IParkingService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingService implements IParkingService {

    @NonNull
    private final IParkingDao parkingDao;

    @Override
    public PageDTO<List<ParkingDTO>> getAllObject(Map<String, Object> queryParams) {
        return parkingDao.select(queryParams);
    }

    @Override
    public Optional<ParkingDTO> getObjectById(Long id) {
        return parkingDao.select(id);
    }

    @Override
    public Optional<ParkingDTO> updateObjectById(Long id, ParkingDTO object) {
        return parkingDao.update(id, object);
    }

    @Override
    public Optional<ParkingDTO> deleteObjectById(Long id) {
        return parkingDao.delete(id);
    }

    @Override
    public Optional<ParkingDTO> insertObject(ParkingDTO object) {
        return parkingDao.insert(object);
    }
}
