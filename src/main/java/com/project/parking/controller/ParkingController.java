package com.project.parking.controller;

import com.google.gson.Gson;
import com.project.parking.controller.interfaces.IParkingController;
import com.project.parking.data.dto.ParkingDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.service.behavior.IParkingService;
import com.project.parking.service.behavior.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path = "/parking")
@RequiredArgsConstructor
public class ParkingController implements IParkingController {

    @NonNull
    private final IParkingService parkingService;

    private final Gson gson = new Gson();

    @Override
    public DeferredResult<String> getAllObjectList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(parkingService.getAllObject(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ParkingDTO>> getObjectById(Long id) {
        DeferredResult<Optional<ParkingDTO>> result = new DeferredResult<>();
        try{
            result.setResult(parkingService.getObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ParkingDTO>> updateObjectById(Long id, ParkingDTO object) {
        DeferredResult<Optional<ParkingDTO>> result = new DeferredResult<>();
        try{
            result.setResult(parkingService.updateObjectById(id, object));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ParkingDTO>> deleteObjectById(Long id) {
        DeferredResult<Optional<ParkingDTO>> result = new DeferredResult<>();
        try{
            result.setResult(parkingService.deleteObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ParkingDTO>> insertObject(ParkingDTO object) {
        DeferredResult<Optional<ParkingDTO>> result = new DeferredResult<>();
        try{
            result.setResult(parkingService.insertObject(object));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ParkingDTO>> restoreObjectById(Long id) {
        DeferredResult<Optional<ParkingDTO>> result = new DeferredResult<>();
        try{
            result.setResult(parkingService.restoreObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }
}
