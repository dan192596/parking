package com.project.parking.service.behavior;

import com.project.parking.data.dto.MessageDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;
import com.project.parking.data.model.NewChatModel;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IMessageService extends IMaintenanceService<MessageDTO, Long> {
    PageDTO<List<MessageDTO>> getAllMessagesByChatList(Map<String, Object> queryParams);

    Optional<MessageDTO> insertChat(NewChatModel object);
}
