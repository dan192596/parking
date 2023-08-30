package com.project.parking.service.behavior;

import com.project.parking.data.dto.MessageDTO;
import com.project.parking.data.dto.ParkingDTO;
import com.project.parking.data.model.NewChatModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.Optional;

public interface IParkingService extends IMaintenanceService<ParkingDTO, Long> {
    Optional<ParkingDTO> restoreObjectById(Long id);
}
