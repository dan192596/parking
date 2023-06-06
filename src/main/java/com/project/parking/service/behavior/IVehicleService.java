package com.project.parking.service.behavior;

import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;

import java.util.List;
import java.util.Map;

public interface IVehicleService extends IMaintenanceService<VehicleDTO, Long>{

    PageDTO<List<VehicleDTO>> getAllVehicleByUserList(Map<String, Object> queryParams);
}
