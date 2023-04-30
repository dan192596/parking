package com.project.parking.controller.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
//import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Component
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public interface IMantainanceController<T,K> {

    /**
     * Obtiene un listado de datos de una tabla de objetos
     *
     * @param queryParams > Filtros de datos
     * @return Listado de datos
     */
    @GetMapping(path = "",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<String> getAllObjectList(@RequestHeader Integer userId, @RequestParam Map<String, Object> queryParams);
//    public DeferredResult<Optional<PageDTO<List<T>>>> getAllObjectList(@RequestHeader Integer userId, @RequestParam Map<String, Object> queryParams);

    /**
     * Obtiene un objeto identificado por su id
     * @param id > Id del objeto requerido
     * @return Objeto encontrado
     */
    @GetMapping(path = "/{id}",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<Optional<T>> getObjectById(@RequestHeader Integer userId, @PathVariable("id") K id);

    /**
     * Actualiza un objeto de una tabla a partir de su id
     * @param id > Id del objeto a actualizar
     * @param object > Objeto actualizado
     * @return Resultado de la transaccion
     */
    @PutMapping(path = "/{id}",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<Optional<T>> updateObjectById(@RequestHeader Integer userId, @PathVariable("id") K id, @RequestBody @Valid T object);

    /**
     * Elimina un objeto de una tabla a partir de su id
     * @param id > Id del objeto a eliminar
     * @return Resultado de la transaccion
     */
    @DeleteMapping(path = "/{id}",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<Optional<T>> deleteObjectById(@RequestHeader Integer userId, @PathVariable("id") K id);

    /**
     * Inserta un nuevo objeto a una tabla
     * @param object > Datos del objeto a insertar
     * @return Resultado de la transaccion
     */
    @PostMapping(path = "",
            produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public DeferredResult<Optional<T>> insertObject(@RequestHeader Integer userId, @RequestBody @Valid T object);
}
