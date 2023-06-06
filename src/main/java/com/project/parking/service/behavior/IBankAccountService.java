package com.project.parking.service.behavior;

import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;

import java.util.List;
import java.util.Map;

public interface IBankAccountService extends IMaintenanceService<BankAccountDTO, Long> {
    PageDTO<List<BankAccountDTO>> getAllAccountsByUserList(Map<String, Object> queryParams);
}
