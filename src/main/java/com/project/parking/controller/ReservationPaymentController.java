package com.project.parking.controller;

import com.google.gson.Gson;
import com.project.parking.controller.interfaces.IReservationPaymentController;
import com.project.parking.data.dto.ReservationPaymentDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.service.behavior.IReservationPaymentService;
import com.project.parking.service.behavior.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/payment")
@RequiredArgsConstructor
public class ReservationPaymentController implements IReservationPaymentController {

    @NonNull
    private final IReservationPaymentService reservationPaymentService;

    private final Gson gson = new Gson();

    @Override
    public DeferredResult<String> getAllObjectList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(reservationPaymentService.getAllObject(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ReservationPaymentDTO>> getObjectById(Long id) {
        DeferredResult<Optional<ReservationPaymentDTO>> result = new DeferredResult<>();
        try{
            result.setResult(reservationPaymentService.getObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ReservationPaymentDTO>> updateObjectById(Long id, ReservationPaymentDTO object) {
        DeferredResult<Optional<ReservationPaymentDTO>> result = new DeferredResult<>();
        try{
            result.setResult(reservationPaymentService.updateObjectById(id, object));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ReservationPaymentDTO>> deleteObjectById(Long id) {
        DeferredResult<Optional<ReservationPaymentDTO>> result = new DeferredResult<>();
        try{
            result.setResult(reservationPaymentService.deleteObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ReservationPaymentDTO>> insertObject(ReservationPaymentDTO object) {
        DeferredResult<Optional<ReservationPaymentDTO>> result = new DeferredResult<>();
        try{
            result.setResult(reservationPaymentService.insertObject(object));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }
}
