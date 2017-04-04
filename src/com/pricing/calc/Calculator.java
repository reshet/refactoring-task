package com.pricing.calc;

import com.pricing.PriceCalculate;

/**
 * Created by ihorreshetnov on 4/4/17.
 */
public interface Calculator {

    PriceCalculate.Price getDefaultPrice();

    int calculate(Long price1, Long price2);
}
