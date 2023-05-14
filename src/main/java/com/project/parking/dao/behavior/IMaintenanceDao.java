package com.project.parking.dao.behavior;

import com.project.parking.data.dto.PageDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IMaintenanceDao<T, K> {
    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Optional<T> select(K id);

    /**
     *
     * @param queryParams
     * @return
     * @throws Exception
     */
    PageDTO<List<T>> select(Map<String, Object> queryParams);


    /**
     *
     * @param id
     * @param object
     * @return
     * @throws Exception
     */
    Optional<T> update(K id, T object);

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Optional<T> delete(K id);

    /**
     *
     * @param object
     * @return
     * @throws Exception
     */
    Optional<T> insert(T object);
}

