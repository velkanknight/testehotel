package com.rfsystems.hotel.resource;

import com.rfsystems.hotel.model.Hotel;
import com.rfsystems.hotel.service.HotelResponse;
import com.rfsystems.hotel.service.HotelService;
import com.rfsystems.hotel.service.TravelFullPricelService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/")
public class TravelPriceResource {

	@Autowired private TravelFullPricelService travelService;
	@Autowired private HotelService hotelService;

	@GetMapping(value = "/hotel")
	public ResponseEntity<HotelResponse> calcTotalPrice(
								@RequestParam("hotelid") Long hotelId,
								@RequestParam("checkin")  LocalDate checkin,
								@RequestParam("checkout") LocalDate checkout,
								@RequestParam("qtdadult") Long qtdAdult,
								@RequestParam("qtdchild") Long qtdChild) {

		Hotel[] hotels = hotelService.getHotelById(hotelId);
		HotelResponse hotelResponse =
				travelService.calcFullPrice(checkin, checkout, qtdAdult, qtdChild, hotels[0]);
		return ResponseEntity.status(HttpStatus.OK).body(hotelResponse);
    }

	@GetMapping(value = "/hotels")
	public ResponseEntity<List<HotelResponse>> calcTotalPriceByCityCode(
								 @RequestParam("citycode") Long cityCode,
								 @RequestParam("checkin")  LocalDate checkin,
								 @RequestParam("checkout") LocalDate checkout,
								 @RequestParam("qtdadult") Long qtdAdult,
								 @RequestParam("qtdchild") Long qtdChild) {
		long ini = System.currentTimeMillis();
		List<Hotel> hotelList = hotelService.getHotelByCityCode(cityCode);
		List<HotelResponse> hotelResponseList = null;
		try {
			hotelResponseList = travelService.calcFullPriceHotels(checkin, checkout, qtdAdult, qtdChild, hotelList);
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("time:" + (System.currentTimeMillis() - ini));
		return ResponseEntity.status(HttpStatus.OK).body(hotelResponseList);
	}

}











