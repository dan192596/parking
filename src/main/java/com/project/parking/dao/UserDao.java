package com.project.parking.dao;

import com.project.parking.dao.behavior.IUserDao;
import com.project.parking.data.defaults.StatusDefault;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.User;
import com.project.parking.data.model.DefaultsParamsModel;
import com.project.parking.data.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDao implements IUserDao {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final StatusDefault statusDefault;

    @Override
    public Optional<UserDTO> select(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserDTO::new);
    }

    @Override
    public PageDTO<List<UserDTO>> select(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<User> users = userRepository.findAll(pageable);

        return new PageDTO<>(
                users.getContent()
                        .stream()
                        .map(UserDTO::new)
                        .collect(Collectors.toList()),
                users.getTotalElements(),
                params.getIndex(),
                users.getContent().size()
        );

    }

    @Override
    public Optional<UserDTO> update(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> {
            user.setName(userDTO.getName());
            user.setLastname(userDTO.getLastname());
            user.setDocument(userDTO.getDocument());
            user.setBirthday(userDTO.getBirthday());
            user.setPhone(userDTO.getPhone());
            user.setEmail(userDTO.getEmail());
            long time = new Date().getTime();
            user.setUpdatedDatetime(new Date(time));
            userRepository.save(user);
        });
        return userOptional.map(UserDTO::new);
    }

    @Override
    public Optional<UserDTO> delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> {
            //Cambiar estatus a deshabilitado
        } );
        return userOptional.map(UserDTO::new);
    }

    @Override
    public Optional<UserDTO> insert(UserDTO userDTO) {
        boolean userExist = userRepository.existsByEmail(userDTO.getEmail());
        if(userExist){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario existente");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setDocument(userDTO.getDocument());
        user.setBirthday(userDTO.getBirthday());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setStatus(statusDefault.getEnabled());
        user.setIdentifier(userDTO.getUuid());
        return Optional.of(new UserDTO(userRepository.save(user)));
    }

    @Override
    public Optional<UserDTO> select(String uuid) {
        Optional<User> user = userRepository.findByIdentifier(uuid);
        return user.map(UserDTO::new);
    }
}
