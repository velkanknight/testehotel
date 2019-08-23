package com.rfsystems.hotel.model;

import com.rfsystems.hotel.service.HotelResponse;
import com.rfsystems.hotel.service.PriceDetail;
import com.rfsystems.hotel.service.RoomResponse;
import com.rfsystems.hotel.service.Utils;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

public class HotelResponseCallable implements Callable<HotelResponse> {

    private Hotel hotel;
    private HotelResponse hotelResponse;
    private final Long qtdAdult;
    private final Long qtdChild;
    private final int days;

    public HotelResponseCallable(final Hotel hotel,final HotelResponse hotelResponse, final Long qtdAdult, final Long qtdChild, final int days){
        this.hotel = hotel;
        this.hotelResponse = hotelResponse;
        this.qtdAdult = qtdAdult;
        this.qtdChild = qtdChild;
        this.days = days;
    }

    @Override
    public HotelResponse call() throws Exception {
        List<RoomResponse> listRoomResponse = new Vector();

        for (Room room : this.hotel.getRooms()) {
            hotelResponse.setId(hotel.getId()).setCityName(hotel.getCityName());
            RoomResponse roomResponse = new RoomResponse();
            Double priceAdult = qtdAdult * room.getPrice().getAdult() * days;
            Double priceChild = qtdChild * room.getPrice().getChild() * days;
            Double totalPrice = Utils.calcularComissao(priceAdult) + Utils.calcularComissao(priceChild);
            PriceDetail priceDetail = new PriceDetail(room.getPrice().getAdult(), room.getPrice().getChild());
            roomResponse.setTotalPrice(Utils.truncate(totalPrice)).setRoomID(room.getRoomID()).setCategoryName(room.getCategoryName()).setPriceDetail(priceDetail);
            listRoomResponse.add(roomResponse);
            hotelResponse.setRooms(listRoomResponse);
        }
            return hotelResponse;

    }

}
