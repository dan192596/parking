package com.project.parking.dao.behavior;

import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;

import java.util.List;
import java.util.Map;

public interface IBankAccountDao extends IMaintenanceDao<BankAccountDTO, Long> {
    PageDTO<List<BankAccountDTO>> getAllAccountsByUserList(Map<String, Object> queryParams);
}
