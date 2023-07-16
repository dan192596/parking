package com.project.parking.service;

import com.project.parking.dao.behavior.IReservationDao;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ReservationDTO;
import com.project.parking.service.behavior.IReservationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {

    @NonNull
    private final IReservationDao reservationDao;

    @Override
    public PageDTO<List<ReservationDTO>> getAllObject(Map<String, Object> queryParams) {
        return reservationDao.select(queryParams);
    }

    @Override
    public Optional<ReservationDTO> getObjectById(Long id) {
        return reservationDao.select(id);
    }

    @Override
    public Optional<ReservationDTO> updateObjectById(Long id, ReservationDTO object) {
        return reservationDao.update(id, object);
    }

    @Override
    public Optional<ReservationDTO> deleteObjectById(Long id) {
        return reservationDao.delete(id);
    }

    @Override
    public Optional<ReservationDTO> insertObject(ReservationDTO object) {
        return reservationDao.insert(object);
    }

    @Override
    public PageDTO<List<ReservationDTO>> getAllReservationsByUserList(Map<String, Object> queryParams) {
        return reservationDao.getAllReservationsByUserList(queryParams);
    }
}
