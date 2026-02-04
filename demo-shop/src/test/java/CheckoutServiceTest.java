import org.junit.jupiter.api.Test;
import uk.merkator.CheckoutService;
import uk.merkator.Offer;
import uk.merkator.PriceListRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutServiceTest {

    // PriceListSRepository Should be mocked but for simplicity I'm  using real instance
    private final PriceListRepository priceListRepository = new PriceListRepository();
    private final List<Offer> offers = new ArrayList<>();

    private final CheckoutService checkoutService = new CheckoutService(priceListRepository, offers);

    @Test
    public void testCalculateTotalPrice() {
        List<String> items = Arrays.asList("Apple", "Apple", "Orange", "Apple");
        BigDecimal totalPrice = checkoutService.getBasketAmount(items);
        assertEquals(0, BigDecimal.valueOf(2.05).compareTo(totalPrice));
    }

    @Test
    public void testCalculateTotalPriceWithUnknowProduct() {
        List<String> items = Arrays.asList("Apple", "Apple", "Orange", "Apple", "Banana");
        BigDecimal totalPrice = checkoutService.getBasketAmount(items);
        assertEquals(0, BigDecimal.valueOf(2.05).compareTo(totalPrice));
    }
}
