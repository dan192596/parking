package com.project.parking.controller;

import com.google.gson.Gson;
import com.project.parking.controller.interfaces.IMessageController;
import com.project.parking.data.dto.MessageDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.service.behavior.IMessageService;
import com.project.parking.service.behavior.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path = "/message")
@RequiredArgsConstructor
public class MessageController implements IMessageController {

    @NonNull
    private final IMessageService messageService;

    private final Gson gson = new Gson();


    @Override
    public DeferredResult<String> getAllObjectList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(messageService.getAllObject(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<MessageDTO>> getObjectById(Long id) {
        DeferredResult<Optional<MessageDTO>> result = new DeferredResult<>();
        try{
            result.setResult(messageService.getObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<MessageDTO>> updateObjectById(Long id, MessageDTO object) {
        DeferredResult<Optional<MessageDTO>> result = new DeferredResult<>();
        try{
            result.setResult(messageService.updateObjectById(id, object));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<MessageDTO>> deleteObjectById(Long id) {
        DeferredResult<Optional<MessageDTO>> result = new DeferredResult<>();
        try{
            result.setResult(messageService.deleteObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<MessageDTO>> insertObject(MessageDTO object) {
        DeferredResult<Optional<MessageDTO>> result = new DeferredResult<>();
        try{
            result.setResult(messageService.insertObject(object));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<String> getAllMessagesByChatList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(messageService.getAllMessagesByChatList(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }
}
