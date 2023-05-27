package com.project.parking.controller.interfaces;

import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.model.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserController extends IMantainanceController<UserDTO,Long> {

    /*
    * Login by uuid
    *
    *
    * */
    @GetMapping(path = "/{uuid}",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<Optional<UserDTO>> getObjectByIdentifier(@PathVariable("uuid") String id);

//    /*
//    * Create a new user
//    * */
//    @PostMapping(path = "",
//            produces = "application/json")
//    @ResponseStatus(code = HttpStatus.OK)
//    @ResponseBody
//    public DeferredResult<Optional<UserDTO>> registerUser(@RequestBody @Valid UserModel user);
}
