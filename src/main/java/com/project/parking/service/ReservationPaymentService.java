package com.project.parking.service;

import com.project.parking.dao.behavior.IReservationPaymentDao;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ReservationPaymentDTO;
import com.project.parking.service.behavior.IReservationPaymentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationPaymentService implements IReservationPaymentService {

    @NonNull
    private final IReservationPaymentDao reservationPaymentDao;

    @Override
    public PageDTO<List<ReservationPaymentDTO>> getAllObject(Map<String, Object> queryParams) {
        return reservationPaymentDao.select(queryParams);
    }

    @Override
    public Optional<ReservationPaymentDTO> getObjectById(Long id) {
        return reservationPaymentDao.select(id);
    }

    @Override
    public Optional<ReservationPaymentDTO> updateObjectById(Long id, ReservationPaymentDTO object) {
        return reservationPaymentDao.update(id, object);
    }

    @Override
    public Optional<ReservationPaymentDTO> deleteObjectById(Long id) {
        return reservationPaymentDao.delete(id);
    }

    @Override
    public Optional<ReservationPaymentDTO> insertObject(ReservationPaymentDTO object) {
        return reservationPaymentDao.insert(object);
    }
}
