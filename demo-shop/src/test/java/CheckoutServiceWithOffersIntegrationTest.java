import org.junit.jupiter.api.Test;
import uk.merkator.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutServiceWithOffersIntegrationTest {

    // PriceListSRepository Should be mocked but for simplicity I'm  using real instance
    private final PriceListRepository priceListRepository = new PriceListRepository();
    private final List<Offer> offers = new ArrayList<>() {
        {
            add(new ApplesOffer(priceListRepository));
            add(new OrangesOffer(priceListRepository));
        }
    };

    private final CheckoutService checkoutService = new CheckoutService(priceListRepository, offers);

    @Test
    public void testCalculateTotalPrice() {
        // 0.6 + 0.0 (apple offer) + 0.25 + 0.6 = 1.45
        List<String> items = Arrays.asList("Apple", "Apple", "Orange", "Apple");
        BigDecimal totalPrice = checkoutService.getBasketAmount(items);
        assertEquals(0, BigDecimal.valueOf(1.45).compareTo(totalPrice));
    }

    @Test
    public void testCalculateTotalPriceWithTwoOffers() {
        // 0.6 + 0.0 (apple offer) + 0.25 + 0.25  + 0.0 (orange offer)  = 1.10
        List<String> items = Arrays.asList("Apple", "Apple", "Orange", "Orange", "Orange");
        BigDecimal totalPrice = checkoutService.getBasketAmount(items);
        assertEquals(0, BigDecimal.valueOf(1.10).compareTo(totalPrice));
    }

    @Test
    public void testCalculateTotalPriceWithUnknowProduct() {
        // 0.6 + 0.0 (apple offer) + 0.25 + 0.25  + 0.0 (orange offer)  = 1.10
        List<String> items = Arrays.asList("Apple", "Apple", "Orange", "Orange", "Orange", "Banana");
        BigDecimal totalPrice = checkoutService.getBasketAmount(items);
        assertEquals(0, BigDecimal.valueOf(1.10).compareTo(totalPrice));
    }
}
