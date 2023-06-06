package com.project.parking.service.behavior;

import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.ChatDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;

import java.util.List;
import java.util.Map;

public interface IChatService extends IMaintenanceService<ChatDTO,Long> {
    PageDTO<List<ChatDTO>> getAllChatsByUserList(Map<String, Object> queryParams);
}
