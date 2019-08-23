package com.rfsystems.hotel.service;

import java.util.List;

public class HotelResponse {

    private Long id;
    private String cityName;
    private List<RoomResponse> rooms;

    public Long getId() {
        return id;
    }

    public HotelResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public HotelResponse setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public List<RoomResponse> getRooms() {
        return rooms;
    }

    public HotelResponse setRooms(List<RoomResponse> rooms) {
        this.rooms = rooms;
        return this;
    }
}
