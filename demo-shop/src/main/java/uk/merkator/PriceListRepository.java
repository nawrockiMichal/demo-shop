package uk.merkator;

import java.math.BigDecimal;

public class PriceListRepository {

    public BigDecimal getPrice(String itemName) {
        switch (itemName) {
            case "Apple":
                return BigDecimal.valueOf(0.60);
            case "Orange":
                return BigDecimal.valueOf(0.25);
            default:
                return BigDecimal.ZERO;
        }
    }
}
