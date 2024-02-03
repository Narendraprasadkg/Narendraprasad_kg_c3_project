import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's Cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet Corn Soup", 119);
        restaurant.addToMenu("Vegetable Lasagne", 269);
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

    @Test
    public void getOrderValue_shouldThrowExceptionForItemNotFound() {
        List<String> selectedItems = new ArrayList<>();
        selectedItems.add("Sweet Corn Soup");
        selectedItems.add("Fried Rice"); // Assuming this item is not in the menu

        assertThrows(ItemNotFoundException.class,
                () -> restaurant.getOrderValue(selectedItems), "Should throw exception for item not found");
    }

    @Test
    public void getOrderValue_shouldReturnCorrectOrderValueForSelectedItems() throws ItemNotFoundException {
        List<String> selectedItems = new ArrayList<>();
        selectedItems.add("Sweet Corn Soup");
        selectedItems.add("Vegetable Lasagne");

        int orderValue = restaurant.getOrderValue(selectedItems);
        int expectedOrderValue = 119 + 269;
        assertEquals(expectedOrderValue, orderValue, "Should return correct order value for selected items");
    }

    @Test
    public void addingItemToMenu_shouldIncreaseMenuSizeBy1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling Brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size(), "Should increase menu size by 1");
    }

    @Test
    public void removingItemFromMenu_shouldDecreaseMenuSizeBy1() throws ItemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable Lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size(), "Should decrease menu size by 1");
    }

    @Test
    public void removingItemThatDoesNotExist_shouldThrowException() {
        assertThrows(ItemNotFoundException.class,
                () -> restaurant.removeFromMenu("French Fries"), "Should throw exception for item not found");
    }
}
