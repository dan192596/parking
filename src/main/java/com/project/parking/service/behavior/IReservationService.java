package com.project.parking.service.behavior;

import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ReservationDTO;

import java.util.List;
import java.util.Map;

public interface IReservationService extends IMaintenanceService<ReservationDTO, Long> {

    PageDTO<List<ReservationDTO>> getAllReservationsByUserList(Map<String, Object> queryParams);
}
