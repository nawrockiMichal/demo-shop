import org.junit.jupiter.api.Test;
import uk.merkator.ApplesOffer;
import uk.merkator.PriceListRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppleOfferTest {

    private final PriceListRepository priceListRepository = new PriceListRepository();
    private final ApplesOffer applesOffer = new ApplesOffer(priceListRepository);

    @Test
    public void noDiscountWhenEmpty() {
        List<String> items = Arrays.asList();
        BigDecimal discount = applesOffer.getValue(items);
        assertEquals(0, BigDecimal.valueOf(0).compareTo(discount));
    }

    @Test
    public void noDiscountForOneApple() {
        List<String> items = Arrays.asList("Apple");
        BigDecimal discount = applesOffer.getValue(items);
        assertEquals(0, BigDecimal.valueOf(0).compareTo(discount));
    }

    @Test
    public void shouldDiscountOneAppleForEveryTwo() {
        List<String> items = Arrays.asList("Apple", "Apple");
        BigDecimal discount = applesOffer.getValue(items);
        assertEquals(0, BigDecimal.valueOf(0.6).compareTo(discount));
    }

    @Test
    public void shouldDiscountOneAppleForEveryTwoWhenSixApples() {
        List<String> items = Arrays.asList("Apple", "Apple", "Apple", "Apple", "Apple", "Apple");
        BigDecimal discount = applesOffer.getValue(items);
        assertEquals(0, BigDecimal.valueOf(1.8).compareTo(discount));
    }

    @Test
    public void shouldDiscountOneAppleForEveryTwoWhenSixApplesAndOneOrange() {
        List<String> items = Arrays.asList("Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Orange");
        BigDecimal discount = applesOffer.getValue(items);
        assertEquals(0, BigDecimal.valueOf(1.8).compareTo(discount));
    }
}
