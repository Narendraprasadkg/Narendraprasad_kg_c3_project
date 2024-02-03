import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;  // Add this import for List

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void isRestaurantOpen_shouldReturnTrueIfTimeIsBetweenOpeningAndClosingTime() {
        LocalTime currentTime = LocalTime.parse("15:00:00");
        restaurant.setCurrentTime(currentTime);
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void isRestaurantOpen_shouldReturnFalseIfTimeIsOutsideOpeningAndClosingTime() {
        LocalTime currentTime = LocalTime.parse("09:00:00");
        restaurant.setCurrentTime(currentTime);
        assertFalse(restaurant.isRestaurantOpen());
    }

    // Fail test case before the order value feature develop
    @Test
    public void getOrderValue_shouldThrowExceptionForItemNotFound() {
        List<String> selectedItems = new ArrayList<>();
        selectedItems.add("Sweet corn soup");
        selectedItems.add("Fried Rice"); // Assuming this item is not in the menu

        assertThrows(ItemNotFoundException.class,
                () -> restaurant.getOrderValue(selectedItems));
    }

    @Test
    public void addingItemToMenu_shouldIncreaseMenuSizeBy1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removingItemFromMenu_shouldDecreaseMenuSizeBy1() throws ItemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removingItemThatDoesNotExist_shouldThrowException() {
        assertThrows(ItemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
}
