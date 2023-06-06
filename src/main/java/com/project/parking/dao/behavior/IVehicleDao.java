package com.project.parking.dao.behavior;

import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;

import java.util.List;
import java.util.Map;

public interface IVehicleDao extends IMaintenanceDao<VehicleDTO, Long> {
    PageDTO<List<VehicleDTO>> getAllVehicleByUserList(Map<String, Object> queryParams);
}
