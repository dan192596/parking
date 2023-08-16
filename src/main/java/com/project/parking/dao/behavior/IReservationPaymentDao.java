package com.project.parking.dao.behavior;

import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ReservationDTO;
import com.project.parking.data.dto.ReservationPaymentDTO;

import java.util.List;
import java.util.Map;

public interface IReservationPaymentDao extends IMaintenanceDao<ReservationPaymentDTO, Long>{

    PageDTO<List<ReservationPaymentDTO>> getPaymentsByUserConsole(Map<String, Object> queryParams);
}
