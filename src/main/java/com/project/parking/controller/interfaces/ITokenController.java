package com.project.parking.controller.interfaces;

import com.project.parking.data.dto.BasicResponseDTO;
import com.project.parking.data.dto.TokenDTO;
import com.project.parking.data.model.BasicTokenModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@Component
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public interface ITokenController {

    /**
     * Crea un nuevo token
     * @return Respuesta basica con estatus 200
     */
    @PostMapping(path = "",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<BasicResponseDTO> createToken(@RequestBody @Valid BasicTokenModel tokenModel);

    /**
     * Valido token
     * @return Respuesta basica con estatus 200
     */
    @PostMapping(path = "/validate",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<Optional<TokenDTO>> validateToken(@RequestBody @Valid BasicTokenModel tokenModel);
}
