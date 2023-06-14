package com.project.parking.controller;

import com.google.gson.Gson;
import com.project.parking.controller.interfaces.IBankAccountController;
import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.service.behavior.IBankAccountService;
import com.project.parking.service.behavior.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/account")
@RequiredArgsConstructor
public class BankAccountController implements IBankAccountController {

    @NonNull
    private final IBankAccountService accountService;

    private final Gson gson = new Gson();

    @Override
    public DeferredResult<String> getAllObjectList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(accountService.getAllObject(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<BankAccountDTO>> getObjectById(Long id) {
        DeferredResult<Optional<BankAccountDTO>> result = new DeferredResult<>();
        try{
            result.setResult(accountService.getObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<BankAccountDTO>> updateObjectById(Long id, BankAccountDTO object) {
        DeferredResult<Optional<BankAccountDTO>> result = new DeferredResult<>();
        try{
            result.setResult(accountService.updateObjectById(id, object));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<BankAccountDTO>> deleteObjectById(Long id) {
        DeferredResult<Optional<BankAccountDTO>> result = new DeferredResult<>();
        try{
            result.setResult(accountService.deleteObjectById(id));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<BankAccountDTO>> insertObject(BankAccountDTO object) {
        DeferredResult<Optional<BankAccountDTO>> result = new DeferredResult<>();
        try{
            result.setResult(accountService.insertObject(object));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }

    @Override
    public DeferredResult<String> getAllAccountsByUserList(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        try{
            result.setResult(gson.toJson(accountService.getAllAccountsByUserList(queryParams)));
        }catch (Exception e){
            result.setErrorResult(e);
        }
        return result;
    }
}
