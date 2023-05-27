package com.project.parking.controller;

import com.google.gson.Gson;
import com.project.parking.controller.interfaces.IUserController;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.model.UserModel;
import com.project.parking.service.behavior.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController implements IUserController {

    @NonNull
    private final IUserService userService;

    private final Gson gson = new Gson();

    @Override
    public DeferredResult<String> getAllObjectList( Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(userService.getAllObject(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<UserDTO>> getObjectById(Long id) {
        DeferredResult<Optional<UserDTO>> result = new DeferredResult<>();
        try{
            result.setResult(userService.getObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<UserDTO>> updateObjectById(Long id, UserDTO object) {
        DeferredResult<Optional<UserDTO>> result = new DeferredResult<>();
        try{
            result.setResult(userService.updateObjectById(id, object));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<UserDTO>> deleteObjectById(Long id) {
        DeferredResult<Optional<UserDTO>> result = new DeferredResult<>();
        try{
            result.setResult(userService.deleteObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<UserDTO>> insertObject(UserDTO object) {
        DeferredResult<Optional<UserDTO>> result = new DeferredResult<>();
        try{
            result.setResult(userService.insertObject(object));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<UserDTO>> getObjectByIdentifier(String id) {
        DeferredResult<Optional<UserDTO>> result = new DeferredResult<>();
        try{
            result.setResult(userService.select(id));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

}
