package com.pricing.test;

import com.pricing.PriceCalculate;

/**
 * Created by ihorreshetnov on 4/4/17.
 */
public class TestPriceCalculator implements com.pricing.calc.Calculator {

    @Override
    public PriceCalculate.Price getDefaultPrice() {
        return null;
    }

    @Override
    public int calculate(Long price1, Long price2) {
        return 0;
    }
}
