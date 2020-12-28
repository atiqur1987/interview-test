package com.travix.medusa.busyflights.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

@Service
public class PriceCalculatorService {

    private static final int DECIMALS_POINT = 2;

    public BigDecimal basePrice(double price) {
        return new BigDecimal(price).setScale(DECIMALS_POINT, HALF_UP);
    }

    public BigDecimal priceWithDiscount(double basePrice, double discount, double tax) {
        double finalPrice = basePrice - (basePrice * discount / 100) + tax;
        return valueOf(finalPrice).setScale(DECIMALS_POINT, HALF_UP);
    }
}
