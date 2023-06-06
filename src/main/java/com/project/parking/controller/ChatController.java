package com.project.parking.controller;

import com.google.gson.Gson;
import com.project.parking.controller.interfaces.IChatController;
import com.project.parking.data.dto.ChatDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.Chat;
import com.project.parking.service.behavior.IChatService;
import com.project.parking.service.behavior.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/chat")
@RequiredArgsConstructor
public class ChatController implements IChatController {

    @NonNull
    private final IChatService chatService;

    private final Gson gson = new Gson();

    @Override
    public DeferredResult<String> getAllObjectList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(chatService.getAllObject(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ChatDTO>> getObjectById(Long id) {
        DeferredResult<Optional<ChatDTO>> result = new DeferredResult<>();
        try{
            result.setResult(chatService.getObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ChatDTO>> updateObjectById(Long id, ChatDTO object) {
        DeferredResult<Optional<ChatDTO>> result = new DeferredResult<>();
        try{
            result.setResult(chatService.updateObjectById(id, object));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ChatDTO>> deleteObjectById(Long id) {
        DeferredResult<Optional<ChatDTO>> result = new DeferredResult<>();
        try{
            result.setResult(chatService.deleteObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<ChatDTO>> insertObject(ChatDTO object) {
        DeferredResult<Optional<ChatDTO>> result = new DeferredResult<>();
        try{
            result.setResult(chatService.insertObject(object));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<String> getAllChatsByUserList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(chatService.getAllChatsByUserList(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }
}
