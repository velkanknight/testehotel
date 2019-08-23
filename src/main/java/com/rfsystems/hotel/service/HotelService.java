package com.rfsystems.hotel.service;

import com.rfsystems.hotel.model.Hotel;
import javafx.application.Application;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@Service
public class HotelService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Async
    public Hotel[] getHotelById(Long id){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/" + id, Hotel[].class);
    }

    @Async
    public List<Hotel> getHotelByCityCode(Long cityCode){
        RestTemplate restTemplate = new RestTemplate();
        Hotel[] listHoteis = restTemplate.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/avail/" + cityCode, Hotel[].class);
        return Arrays.asList(listHoteis);
    }


}
