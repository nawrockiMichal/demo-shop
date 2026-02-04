import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.merkator.OrangesOffer;
import uk.merkator.PriceListRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrangeOfferTest {

    private final PriceListRepository priceListRepository = new PriceListRepository();
    private final OrangesOffer orangesOffer = new OrangesOffer(priceListRepository);

    @Test
    public void noDiscountWhenEmpty() {
        List<String> items = Arrays.asList();
        BigDecimal discount = orangesOffer.getValue(items);
        assertEquals(0, BigDecimal.valueOf(0).compareTo(discount));
    }
    @Test
    public void shouldDiscountOneOrangeForEveryThree() {
        List<String> items = Arrays.asList("Orange", "Orange", "Orange");
        BigDecimal discount = orangesOffer.getValue(items);
        assertEquals(0, BigDecimal.valueOf(0.25).compareTo(discount));
    }

    @Test
    public void noDiscountForTwoOranges() {
        List<String> items = Arrays.asList("Orange", "Orange");
        BigDecimal discount = orangesOffer.getValue(items);
        assertEquals(0, BigDecimal.valueOf(0).compareTo(discount));
    }

    @Test
    public void shouldDiscountOneOrangeForEveryThreeWhenSixOranges() {
        List<String> items = Arrays.asList("Orange", "Orange", "Orange", "Orange", "Orange", "Orange");
        BigDecimal discount = orangesOffer.getValue(items);
        assertEquals(0, BigDecimal.valueOf(0.5).compareTo(discount));
    }

    @Test
    public void shouldDiscountOneOrangeForEveryThreeWhenSixOrangesAndOneApple() {
        List<String> items = Arrays.asList("Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Apple");
        BigDecimal discount = orangesOffer.getValue(items);
        assertEquals(0, BigDecimal.valueOf(0.5).compareTo(discount));
    }
}
