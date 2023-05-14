package com.project.parking.controller.interfaces;

import com.project.parking.data.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserController extends IMantainanceController<UserDTO,Long> {

}
