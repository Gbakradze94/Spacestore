package com.onlinestore.admin.shippingrate;

import com.onlinestore.common.entity.ShippingRate;

public class ShippingRateNotFoundException extends Exception{
    public ShippingRateNotFoundException(String message){
        super(message);
    }
}
