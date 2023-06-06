package com.project.parking.controller;

import com.google.gson.Gson;
import com.project.parking.controller.interfaces.ITokenController;
import com.project.parking.data.dto.BasicResponseDTO;
import com.project.parking.data.dto.TokenDTO;
import com.project.parking.data.model.BasicTokenModel;
import com.project.parking.service.behavior.ITokenService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Optional;

@RestController
@RequestMapping(path = "/token")
@RequiredArgsConstructor
public class TokenController implements ITokenController {

    @NonNull
    private final ITokenService tokenService;

    private final Gson gson = new Gson();

    @Override
    public DeferredResult<BasicResponseDTO> createToken(BasicTokenModel tokenModel) {
        DeferredResult<BasicResponseDTO> result = new DeferredResult<>();
        try{
            result.setResult(tokenService.createToken(tokenModel));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

    @Override
    public DeferredResult<Optional<TokenDTO>> validateToken(BasicTokenModel tokenModel) {
        DeferredResult<Optional<TokenDTO>> result = new DeferredResult<>();
        try{
            result.setResult(tokenService.validateToken(tokenModel));
        }catch (Exception e){
            result.setErrorResult(e.getMessage());
        }
        return result;
    }

}
