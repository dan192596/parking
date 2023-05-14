package com.project.parking.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {

    private T data;

    private Long count;

    private Integer index;

    private Integer items;

}
