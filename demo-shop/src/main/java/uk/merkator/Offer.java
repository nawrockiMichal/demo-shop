package uk.merkator;

import java.math.BigDecimal;
import java.util.List;

public interface
Offer {
    BigDecimal getValue(List<String> items);
}
