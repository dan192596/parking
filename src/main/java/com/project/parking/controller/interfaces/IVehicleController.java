package com.project.parking.controller.interfaces;

import com.project.parking.data.dto.VehicleDTO;
import com.project.parking.service.behavior.IMaintenanceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Optional;

public interface IVehicleController extends IMantainanceController<VehicleDTO, Long> {

    @GetMapping(path = "/user",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<String> getAllVehicleByUserList(@RequestParam Map<String, Object> queryParams);
}
