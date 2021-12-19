package com.onlinestore.shipping;


import com.onlinestore.common.entity.Country;
import com.onlinestore.common.entity.ShippingRate;
import org.springframework.data.repository.CrudRepository;

public interface ShippingRateRepository extends CrudRepository<ShippingRate, Integer> {

     public ShippingRate findByCountryAndState(Country country, String state);
}