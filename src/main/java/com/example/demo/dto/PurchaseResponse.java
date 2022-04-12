package com.example.demo.dto;

import lombok.Data;

@Data
public class PurchaseResponse {

    

    private final String orderTrackingNumber;

    public PurchaseResponse(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }
}
    
