package com.project.parking.dao;

import com.project.parking.dao.behavior.IReservationPaymentDao;
import com.project.parking.data.defaults.StatusDefault;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ReservationPaymentDTO;
import com.project.parking.data.entity.BankAccount;
import com.project.parking.data.entity.Reservation;
import com.project.parking.data.entity.ReservationPayment;
import com.project.parking.data.entity.User;
import com.project.parking.data.model.DefaultsParamsModel;
import com.project.parking.data.repository.BankAccountRepository;
import com.project.parking.data.repository.ReservationPaymentRepository;
import com.project.parking.data.repository.ReservationRepository;
import com.project.parking.data.repository.UserRepository;
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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationPaymentDao implements IReservationPaymentDao {

    @NonNull
    private final ReservationPaymentRepository reservationPaymentRepository;

    @NonNull
    private final ReservationRepository reservationRepository;

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final StatusDefault statusDefault;

    @NonNull
    private final BankAccountRepository bankAccountRepository;

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
        Optional<Reservation> reservationOptional = reservationRepository.findById(object.getReservation());
        if(!reservationOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservacion inexistente");
        }
        Reservation reservation = reservationOptional.get();
        if(reservationOptional.get().getStatus().equals(statusDefault.getPayed())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservacion ya pagada");
        }
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findPrincipalByUserId(reservation.getVehicle().getUser().getId());
        if(!bankAccountOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontro cuenta bancaria para realizar el pago");
        }
        //Calculation of the total
//        Float valueHour = reservation.getParking().getPrice_hour();
//        System.out.println(new Date());
//        System.out.println(new Date().getTime());
//        System.out.println(reservation.getStartDatetime());
//        System.out.println(reservation.getStartDatetime().getTime());
//        Long hours = TimeUnit.MILLISECONDS.toHours(new Date().getTime() - reservation.getStartDatetime().getTime());
//        System.out.println("Precio por hora "+valueHour+" horas transcurridas "+hours+" y el total es "+valueHour*hours);
        //Saving payment
        ReservationPayment reservationPayment = new ReservationPayment();
        reservationPayment.setTotal(object.getTotal());
        reservationPayment.setReservation(reservationOptional.get());
        reservationPayment.setBankAccount(bankAccountOptional.get());
        reservationPayment.setStatus(statusDefault.getPayed());

        reservation.setStatus(statusDefault.getPayed());
        reservationRepository.save(reservation);
        return Optional.of(new ReservationPaymentDTO(reservationPaymentRepository.save(reservationPayment)));
    }

    @Override
    public PageDTO<List<ReservationPaymentDTO>> getPaymentsByUserConsole(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<ReservationPayment> reservationPayments = reservationPaymentRepository.findAllByOwnerId(params.getUser(), pageable);

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
}
