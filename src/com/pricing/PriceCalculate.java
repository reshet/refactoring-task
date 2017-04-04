
package com.pricing;

import com.pricing.calc.Calculator;
import com.pricing.calc.PriceCalculator;
import com.pricing.db.DBLogger;
import com.pricing.test.TestPriceCalculator;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PriceCalculate {
    public Price price1;
    public Price price2;
    public Object discounts;
    public Date actionDate;
    public Calculator calculator;
    public Logger pricingLogger;

    public PriceCalculate(Price price1, Price price2) {
        this.price1 = price1;
        this.price2 = price2;
        if (!isTestE()) {
            calculator = new PriceCalculator();
            pricingLogger = DBLogger.getInstance();
        } else {
            calculator = new TestPriceCalculator();
        }
    }

    public Price process(Date processDate) {
        int x = 1;

        if (actionDate.after(processDate)) {
            x = 2;
        }

        int y = price1.getCurrency().equals("dollar") == true ? 2 : 1;

        long finalPrice = calculator.getDefaultPrice().getDateUpdated().after(processDate) ? calculator.calculate(price1.getPrice(), price2.getPrice()) : calculator.getDefaultPrice().getPrice() * x * y;

        if (pricingLogger != null) {
            pricingLogger.log(Level.ALL, "{}", finalPrice);
        }

        Price finalPricePrice = new Price();
        finalPricePrice.setPrice((long) finalPrice);

        return finalPricePrice;
    }

    public boolean applyDiscounts() throws RuntimeException {
        class Processor {
            private Price p;

            Processor(Price p) {
                this.p = p;
            }

            Long process(Function<Price, Long> calculateFunction) {
                return calculateFunction.apply(p);
            }
        }

        if (discounts != null) {
            List discountsList = (List) discounts;

            for (int i = 0; i < discountsList.size(); i++) {
                Long discount = (Long) discountsList.get(0);
                setPrice1(new Price(new Processor(price1).process(new Function<Price, Long>() {
                    @Override
                    public Long apply(Price price) {
                        return price.getPrice() * discount;
                    }
                })));
                setPrice2(new Price(new Processor(price2).process(new Function<Price, Long>() {
                    @Override
                    public Long apply(Price price) {
                        return price.getPrice() * discount;
                    }
                })));
            }

            return true;
        }

        return false;
    }

    public void setDiscounts(List<Long> discounts) {
        this.discounts = discounts;
    }

    public void setPrice1(Price price) {
        this.price1 = price;
    }

    public void setPrice2(Price price) {
        this.price2 = price;
    }

    public boolean isTestE() {
        return !System.getProperty("is.production").equals(true);
    }

    public static class Price {
        Long price;
        String currency;
        Date dateUpdated;

        Price() {

        }

        Price(Long price) {
            this.price = price;
        }

        Long getPrice() {
            return price;
        }

        void setPrice(Long price) {
            this.price = price;
        }

        public String getCurrency() {
            return currency;
        }

        public Date getDateUpdated() {
            return dateUpdated;
        }
    }
}
