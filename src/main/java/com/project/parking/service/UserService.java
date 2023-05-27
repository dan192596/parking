package com.project.parking.service;

import com.project.parking.dao.behavior.IUserDao;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.service.behavior.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @NonNull
    private final IUserDao userDao;

    @Override
    public PageDTO<List<UserDTO>> getAllObject(Map<String, Object> queryParams) {
        return userDao.select(queryParams);
    }

    @Override
    public Optional<UserDTO> getObjectById(Long id) {
        return userDao.select(id);
    }

    @Override
    public Optional<UserDTO> updateObjectById(Long id, UserDTO object) {
        return userDao.update(id, object);
    }

    @Override
    public Optional<UserDTO> deleteObjectById(Long id) {
        return userDao.delete(id);
    }

    @Override
    public Optional<UserDTO> insertObject(UserDTO object) {
        return userDao.insert(object);
    }

    @Override
    public Optional<UserDTO> select(String uuid) {
        return userDao.select(uuid);
    }
}
