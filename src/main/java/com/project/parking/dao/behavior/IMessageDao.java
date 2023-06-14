package com.project.parking.dao.behavior;

import com.project.parking.data.dto.MessageDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;
import com.project.parking.data.model.NewChatModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IMessageDao extends IMaintenanceDao<MessageDTO, Long>{
    PageDTO<List<MessageDTO>> getAllMessagesByChatList(Map<String, Object> queryParams);

    Optional<MessageDTO> insertChat(NewChatModel object);
}
