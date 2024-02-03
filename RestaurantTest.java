import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    private Restaurant restaurant;

    @BeforeEach
    public void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's Cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet Corn Soup", 119);
        restaurant.addToMenu("Vegetable Lasagne", 269);
    }

    @Test
    public void isOpen_shouldReturnTrueIfTimeIsBetweenOpeningAndClosingTime() {
        LocalTime currentTime = LocalTime.parse("15:00:00");
        restaurant.setCurrentTime(currentTime);
        assertTrue(restaurant.isRestaurantOpen(), "Restaurant should be open at 3:00 PM");
    }

    @Test
    public void isOpen_shouldReturnFalseIfTimeIsOutsideOpeningAndClosingTime() {
        LocalTime currentTime = LocalTime.parse("09:00:00");
        restaurant.setCurrentTime(currentTime);
        assertFalse(restaurant.isRestaurantOpen(), "Restaurant should be closed at 9:00 AM");
    }

    @Test
    public void addingItemToMenu_shouldIncreaseMenuSizeBy1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling Brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size(), "Menu size should increase by 1");
    }

    @Test
    public void removingItemFromMenu_shouldDecreaseMenuSizeBy1() throws ItemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable Lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size(), "Menu size should decrease by 1");
    }

    @Test
    public void removingItemThatDoesNotExist_shouldThrowException() {
        assertThrows(ItemNotFoundException.class,
                () -> restaurant.removeFromMenu("French Fries"), "Removing non-existing item should throw exception");
    }
}
