import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NewRestaurant {
    private String restaurantName;
    private String location;
    private LocalTime openTime;
    private LocalTime closeTime;
    private List<Item> menuItems = new ArrayList<>();

    public NewRestaurant(String restaurantName, String location, LocalTime openTime, LocalTime closeTime) {
        this.restaurantName = restaurantName;
        this.location = location;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public boolean isOpen() {
        LocalTime currentTime = getCurrentTime();
        return currentTime.isAfter(openTime) && currentTime.isBefore(closeTime);
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenuItems() {
        return menuItems;
    }

    private Item findItemByName(String itemName) {
        for (Item item : menuItems) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String itemName, int itemPrice) {
        Item newItem = new Item(itemName, itemPrice);
        menuItems.add(newItem);
    }

    public void removeFromMenu(String itemName) throws ItemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new ItemNotFoundException(itemName);

        menuItems.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant: " + restaurantName + "\n"
                + "Location: " + location + "\n"
                + "Opening time: " + openTime + "\n"
                + "Closing time: " + closeTime + "\n"
                + "Menu: " + "\n" + getMenuItems());
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
