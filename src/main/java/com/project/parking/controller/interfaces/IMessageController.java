package com.project.parking.controller.interfaces;

import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

public interface IMessageController extends IMantainanceController<MessageDTO, Long>{
    @GetMapping(path = "/user",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<String> getAllMessagesByChatList(@RequestParam Map<String, Object> queryParams);
}
