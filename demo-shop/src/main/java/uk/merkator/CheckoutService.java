package uk.merkator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CheckoutService {
    private final PriceListRepository priceListRepository;
    private final List<Offer> offers;

    public CheckoutService(PriceListRepository priceListRepository, List<Offer> offers) {
        this.offers = offers;
        this.priceListRepository = priceListRepository;
    }

    public BigDecimal getBasketAmount(List<String> items) {
        BigDecimal basketValue = items.stream().map(priceListRepository::getPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDiscount = offers.stream()
                .map(offer -> offer.getValue(items))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return basketValue.subtract(totalDiscount);
    }

}
