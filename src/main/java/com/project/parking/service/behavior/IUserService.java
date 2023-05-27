package com.project.parking.service.behavior;

import com.project.parking.data.dto.UserDTO;

import java.util.Optional;

public interface IUserService extends IMaintenanceService<UserDTO, Long>{
    Optional<UserDTO> select(String uuid);
}
