package com.project.parking.data.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Data
public class DefaultsParamsModel {

    private Integer index;

    private Integer items;

    private String sort;

    private String direction;

    private Date startDate;

    private Date endDate;

    private Date date;

    private String search;

    private Integer month;

    private Integer year;

    private Long user;

    private Long owner;

    private Long chat;

    private Long status;

    private Float latitude;

    private Float longitude;

    public DefaultsParamsModel(Map<String, Object> queryParams){
        this.setIndex(queryParams.containsKey("index") ? Integer.parseInt((String)queryParams.get("index")) : 0);
        this.setItems(queryParams.containsKey("items") ? Integer.parseInt((String) queryParams.get("items")) : 10);
        this.setSort(queryParams.containsKey("sort") ? (String) queryParams.get("sort") : "id");
        this.setDirection(queryParams.containsKey("direction") ? (String) queryParams.get("direction") : "DESC");
        this.setSearch(queryParams.containsKey("search") ? (String)queryParams.get("search") : null);
        this.setMonth(queryParams.containsKey("month") ? Integer.valueOf((String) queryParams.get("month")) : null);
        this.setYear(queryParams.containsKey("year") ? Integer.valueOf((String) queryParams.get("year")) : null);
        this.setUser(queryParams.containsKey("user") ? Long.valueOf((String) queryParams.get("user")) : null);
        this.setChat(queryParams.containsKey("chat") ? Long.valueOf((String) queryParams.get("chat")) : null);
        this.setStatus(queryParams.containsKey("status") ? Long.valueOf((String) queryParams.get("status")) : null);
        this.setOwner(queryParams.containsKey("owner") ? Long.valueOf((String) queryParams.get("owner")) : null);
        this.setLatitude(queryParams.containsKey("latitude") ? Float.valueOf((String) queryParams.get("latitude")) : null);
        this.setLongitude(queryParams.containsKey("longitude") ? Float.valueOf((String) queryParams.get("longitude")) : null);
        try{
            this.setStartDate(queryParams.containsKey("startDate") ? new SimpleDateFormat("yyyy-MM-dd").parse((String) queryParams.get("startDate")) : null);
            this.setEndDate(queryParams.containsKey("endDate") ? new SimpleDateFormat("yyyy-MM-dd").parse((String) queryParams.get("endDate")) : null);
            this.setDate(queryParams.containsKey("date") ? new SimpleDateFormat("yyyy-MM-dd").parse((String) queryParams.get("date")) : null);
        }catch (Exception e){
            this.setStartDate(null);
            this.setEndDate(null);
            e.printStackTrace();
        }
    }

}
