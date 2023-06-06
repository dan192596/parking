package com.project.parking.dao.behavior;

import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.User;

import java.util.Optional;

public interface IUserDao extends IMaintenanceDao<UserDTO, Long>{
    Optional<UserDTO> select(String uuid);

    Optional<User> selectUser(Long id);
}
