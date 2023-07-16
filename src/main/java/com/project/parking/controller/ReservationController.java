package com.project.parking.controller;

import com.google.gson.Gson;
import com.project.parking.controller.interfaces.IReservationController;
import com.project.parking.data.dto.ReservationDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.service.behavior.IReservationService;
import com.project.parking.service.behavior.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path = "/reservation")
@RequiredArgsConstructor
public class ReservationController implements IReservationController {

    @NonNull
    private final IReservationService reservationService;

    private final Gson gson = new Gson();

    @Override
    public DeferredResult<String> getAllObjectList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(reservationService.getAllObject(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ReservationDTO>> getObjectById(Long id) {
        DeferredResult<Optional<ReservationDTO>> result = new DeferredResult<>();
        try{
            result.setResult(reservationService.getObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ReservationDTO>> updateObjectById(Long id, ReservationDTO object) {
        DeferredResult<Optional<ReservationDTO>> result = new DeferredResult<>();
        try{
            result.setResult(reservationService.updateObjectById(id, object));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ReservationDTO>> deleteObjectById(Long id) {
        DeferredResult<Optional<ReservationDTO>> result = new DeferredResult<>();
        try{
            result.setResult(reservationService.deleteObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ReservationDTO>> insertObject(ReservationDTO object) {
        DeferredResult<Optional<ReservationDTO>> result = new DeferredResult<>();
        try{
            result.setResult(reservationService.insertObject(object));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<String> getAllAccountsByUserList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(reservationService.getAllReservationsByUserList(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }
}
