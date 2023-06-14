package com.project.parking.controller.interfaces;

import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.MessageDTO;
import com.project.parking.data.model.NewChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

public interface IMessageController extends IMantainanceController<MessageDTO, Long>{
    @GetMapping(path = "/chat",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<String> getAllMessagesByChatList(@RequestParam Map<String, Object> queryParams);


    /**
     * Ingresa un mensaje, y si el chat no existe, lo crea
     * @param object > Datos del objeto a insertar
     * @return Resultado de la transaccion
     */
    @PostMapping(path = "/chat",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<Optional<MessageDTO>> insertObject(@RequestBody @Valid NewChatModel object);
}
