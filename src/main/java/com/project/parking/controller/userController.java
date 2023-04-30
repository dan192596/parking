package com.project.parking.controller;

import com.project.parking.controller.interfaces.IUserController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class userController implements IUserController {

    @Override
    public DeferredResult<String> getTest(Map<String, Object> queryParams) {
        DeferredResult<String> result = new DeferredResult<>();
        result.setResult("Prueba de respuesta");
        return result;
    }
}
