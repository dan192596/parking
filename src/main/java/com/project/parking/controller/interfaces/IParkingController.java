package com.project.parking.controller.interfaces;

import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.ParkingDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.Optional;

public interface IParkingController extends IMantainanceController<ParkingDTO, Long>{
    @PatchMapping(path = "/{id}",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<Optional<ParkingDTO>> restoreObjectById(@PathVariable("id") Long id);
}
