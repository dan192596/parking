package com.project.parking.controller.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserController {

    /**
     * Get test value
     * @param queryParams
     * @return test value
     */
    @GetMapping(path = "",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    DeferredResult<String> getTest(@RequestParam Map<String, Object> queryParams);

}
