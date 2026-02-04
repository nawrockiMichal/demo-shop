package uk.merkator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CheckoutService {
    private final PriceListRepository priceListRepository;

    public CheckoutService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    public BigDecimal getBasketAmount(List<String> items) {
        return items.stream().map(priceListRepository::getPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
