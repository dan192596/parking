package com.project.parking.dao.behavior;

import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.ParkingDTO;

import java.util.List;
import java.util.Map;

public interface IParkingDao extends IMaintenanceDao<ParkingDTO, Long>{
    PageDTO<List<ParkingDTO>> selectParkingConsole(Map<String, Object> queryParams);

    PageDTO<List<ParkingDTO>> selectParkingByDistance(Map<String, Object> queryParams);

}
