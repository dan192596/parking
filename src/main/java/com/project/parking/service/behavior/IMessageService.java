package com.project.parking.service.behavior;

import com.project.parking.data.dto.MessageDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;

import java.util.List;
import java.util.Map;

public interface IMessageService extends IMaintenanceService<MessageDTO, Long> {
    PageDTO<List<MessageDTO>> getAllMessagesByChatList(Map<String, Object> queryParams);
}
