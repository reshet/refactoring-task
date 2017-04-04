package com.pricing.calc;

import com.pricing.PriceCalculate;

/**
 * Created by ihorreshetnov on 4/4/17.
 */
public class PriceCalculator implements Calculator {

    @Override
    public PriceCalculate.Price getDefaultPrice() {
        return null;
    }

    @Override
    public int calculate(Long price1, Long price2) {
        return 0;
    }
}
