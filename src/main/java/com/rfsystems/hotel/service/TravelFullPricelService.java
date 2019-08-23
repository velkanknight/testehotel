package com.rfsystems.hotel.service;

import com.rfsystems.hotel.model.Hotel;
import com.rfsystems.hotel.model.HotelResponseCallable;
import com.rfsystems.hotel.model.Room;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

@Service
public class TravelFullPricelService {

    private ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public HotelResponse calcFullPrice(LocalDate checkin, LocalDate checkout, Long qtdAdult, Long qtdChild, Hotel hotel){

        int days = Days.daysBetween(checkin, checkout).getDays();
        List<RoomResponse> listRoomResponse = new ArrayList<>();
        HotelResponse hotelResponse = new HotelResponse();
        RoomResponse roomResponse;
        for (Room room: hotel.getRooms()) {
            hotelResponse.setId(hotel.getId()).setCityName(hotel.getCityName());
            roomResponse = new RoomResponse();
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

    public List<HotelResponse> calcFullPriceHotels(LocalDate checkin, LocalDate checkout, Long qtdAdult, Long qtdChild, List<Hotel> hotels) throws ExecutionException, InterruptedException {

        int days = Days.daysBetween(checkin, checkout).getDays();
        List<HotelResponse> listHotelResponse = new Vector<>();

        for (Hotel hotel: hotels) {
            HotelResponse hotelResponse = new HotelResponse();
            hotelResponse.setId(hotel.getId()).setCityName(hotel.getCityName());
            Future<HotelResponse> result = this.threadPool.submit(new HotelResponseCallable(hotel, hotelResponse, qtdAdult, qtdChild, days));
            listHotelResponse.add(result.get());
        }
        return listHotelResponse;
    }

}
