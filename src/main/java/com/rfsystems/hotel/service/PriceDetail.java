package com.rfsystems.hotel.service;

public class PriceDetail {

    public PriceDetail(Double adult, Double child){
        this.pricePerDayAdult = adult;
        this.pricePerDayChild = child;
    }

    private Double pricePerDayAdult;
    private Double pricePerDayChild;

    public Double getPricePerDayAdult() {
        return pricePerDayAdult;
    }

    public void setPricePerDayAdult(Double pricePerDayAdult) {
        this.pricePerDayAdult = pricePerDayAdult;
    }

    public Double getPricePerDayChild() {
        return pricePerDayChild;
    }

    public void setPricePerDayChild(Double pricePerDayChild) {
        this.pricePerDayChild = pricePerDayChild;
    }

}
