package com.project.parking.service;

import com.project.parking.dao.behavior.ITokenDao;
import com.project.parking.dao.behavior.IUserDao;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.Token;
import com.project.parking.data.model.BasicTokenModel;
import com.project.parking.service.behavior.IMailService;
import com.project.parking.service.behavior.ITokenService;
import com.project.parking.service.behavior.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @NonNull
    private final IUserDao userDao;

    @NonNull
    private final IMailService mailService;

    @NotNull
    private final ITokenDao tokenDao;

    @NotNull
    private final ITokenService tokenService;

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
        Optional<UserDTO> myUser = userDao.insert(object);
        if(myUser.isPresent()){//sent email
            tokenService.createToken(new BasicTokenModel(myUser.get().getId()));
        }
        return myUser;
    }

    @Override
    public Optional<UserDTO> select(String uuid) {
        return userDao.select(uuid);
    }
}
