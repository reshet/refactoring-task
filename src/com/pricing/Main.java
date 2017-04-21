package com.pricing;

public class Main {

    public static void main(String[] args) {
        System.setProperty("is.production", "false");
        PriceCalculate
            priceCalculate =
            new PriceCalculate(new PriceCalculate.Price(20L), new PriceCalculate.Price(30L));
        System.out.println(priceCalculate.calculator.getDefaultPrice());
    }
}
