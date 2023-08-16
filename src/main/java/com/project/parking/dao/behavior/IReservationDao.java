package com.project.parking.dao.behavior;

import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ReservationDTO;

import java.util.List;
import java.util.Map;

public interface IReservationDao extends IMaintenanceDao<ReservationDTO, Long>{
    PageDTO<List<ReservationDTO>> getAllReservationsByUserList(Map<String, Object> queryParams);

    PageDTO<List<ReservationDTO>> getReservationsByUserConsole(Map<String, Object> queryParams);
}
