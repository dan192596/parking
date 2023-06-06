package com.project.parking.dao.behavior;

import com.project.parking.data.dto.ChatDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;

import java.util.List;
import java.util.Map;

public interface IChatDao extends IMaintenanceDao<ChatDTO, Long>{
    PageDTO<List<ChatDTO>> getAllChatsByUserList(Map<String, Object> queryParams);
}
