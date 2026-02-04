package uk.merkator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OrangesOffer implements Offer {

    private final PriceListRepository priceListRepository;

    public OrangesOffer(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    public BigDecimal getValue(List<String> items) {
        BigDecimal itemsDiscounted = BigDecimal.valueOf(items.stream().filter("Orange"::equals).count()).divide(BigDecimal.valueOf(3), RoundingMode.DOWN);
        BigDecimal price = priceListRepository.getPrice("Orange");
        return price.multiply(itemsDiscounted);
    }
}

