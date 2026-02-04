package uk.merkator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ApplesOffer implements Offer {

    private final PriceListRepository priceListRepository;

    public ApplesOffer(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    public BigDecimal getValue(List<String> items) {
        BigDecimal itemsDiscounted = BigDecimal.valueOf(items.stream().filter("Apple"::equals).count()).divide(BigDecimal.valueOf(2), RoundingMode.DOWN);
        BigDecimal price = priceListRepository.getPrice("Apple");
        return price.multiply(itemsDiscounted);
    }
}

