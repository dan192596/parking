package com.project.parking.service.behavior;

import com.project.parking.data.dto.PageDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IMaintenanceService<T,K> {
    /**
     *
     * @param queryParams
     * @return
     * @throws Exception
     */
    PageDTO<List<T>> getAllObject(Map<String, Object> queryParams);

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Optional<T> getObjectById(K id);

    /**
     *
     * @param id
     * @param object
     * @return
     * @throws Exception
     */
    Optional<T> updateObjectById(K id, T object);

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Optional<T> deleteObjectById(K id);

    /**
     *
     * @param object
     * @return
     * @throws Exception
     */
    Optional<T> insertObject(T object);
}

