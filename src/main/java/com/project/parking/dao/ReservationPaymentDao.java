package com.project.parking.dao;

import com.project.parking.dao.behavior.IReservationDao;
import com.project.parking.dao.behavior.IReservationPayment;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ReservationDTO;
import com.project.parking.data.dto.ReservationPaymentDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.ReservationPayment;
import com.project.parking.data.entity.User;
import com.project.parking.data.model.DefaultsParamsModel;
import com.project.parking.data.repository.ReservationPaymentRepository;
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
public class ReservationPaymentDao implements IReservationPayment {

    @NonNull
    private final ReservationPaymentRepository reservationPaymentRepository;


    @Override
    public Optional<ReservationPaymentDTO> select(Long id) {
        Optional<ReservationPayment> reservationPayment = reservationPaymentRepository.findById(id);
        return reservationPayment.map(ReservationPaymentDTO::new);
    }

    @Override
    public PageDTO<List<ReservationPaymentDTO>> select(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<ReservationPayment> reservationPayments = reservationPaymentRepository.findAll(pageable);

        return new PageDTO<>(
                reservationPayments.getContent()
                        .stream()
                        .map(ReservationPaymentDTO::new)
                        .collect(Collectors.toList()),
                reservationPayments.getTotalElements(),
                params.getIndex(),
                reservationPayments.getContent().size()
        );
    }

    @Override
    public Optional<ReservationPaymentDTO> update(Long id, ReservationPaymentDTO object) {
        return Optional.empty();
    }

    @Override
    public Optional<ReservationPaymentDTO> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ReservationPaymentDTO> insert(ReservationPaymentDTO object) {
        return Optional.empty();
    }
}
