package com.project.parking.dao.behavior;

import com.project.parking.data.dto.MessageDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;

import java.util.List;
import java.util.Map;

public interface IMessageDao extends IMaintenanceDao<MessageDTO, Long>{
    PageDTO<List<MessageDTO>> getAllMessagesByChatList(Map<String, Object> queryParams);
}
