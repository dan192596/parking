package com.project.parking.controller;

import com.google.gson.Gson;
import com.project.parking.controller.interfaces.IVehicleController;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.dto.VehicleDTO;
import com.project.parking.service.behavior.IVehicleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/vehicle")
@RequiredArgsConstructor
public class VehicleController implements IVehicleController {

    @NonNull
    private final IVehicleService vehicleService;

    private final Gson gson = new Gson();

    @Override
    public DeferredResult<String> getAllObjectList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(vehicleService.getAllObject(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<VehicleDTO>> getObjectById(Long id) {
        DeferredResult<Optional<VehicleDTO>> result = new DeferredResult<>();
        try{
            result.setResult(vehicleService.getObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<VehicleDTO>> updateObjectById(Long id, VehicleDTO object) {
        DeferredResult<Optional<VehicleDTO>> result = new DeferredResult<>();
        try{
            result.setResult(vehicleService.updateObjectById(id, object));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<VehicleDTO>> deleteObjectById(Long id) {
        DeferredResult<Optional<VehicleDTO>> result = new DeferredResult<>();
        try{
            result.setResult(vehicleService.deleteObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<VehicleDTO>> insertObject(VehicleDTO object) {
        DeferredResult<Optional<VehicleDTO>> result = new DeferredResult<>();
        try{
            result.setResult(vehicleService.insertObject(object));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<String> getAllVehicleByUserList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(vehicleService.getAllVehicleByUserList(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }
}
