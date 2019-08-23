package com.rfsystems.hotel.service;

public class RoomResponse {

    private Long roomID;
    private String categoryName;
    private Double totalPrice;
    private PriceDetail priceDetail;

    public Long getRoomID() {
        return roomID;
    }

    public RoomResponse setRoomID(Long roomID) {
        this.roomID = roomID;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public RoomResponse setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public RoomResponse setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public PriceDetail getPriceDetail() {
        return priceDetail;
    }

    public RoomResponse setPriceDetail(PriceDetail priceDetail) {
        this.priceDetail = priceDetail;
        return this;
    }
}
