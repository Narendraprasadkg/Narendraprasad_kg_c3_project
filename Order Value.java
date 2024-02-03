import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String itemName) {
        super(itemName);
    }
}

class MenuItem {
    private String itemName;
    private int itemPrice;

    public MenuItem(String itemName, int itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    @Override
    public String toString() {
        return itemName + " - " + itemPrice;
    }
}

public class NewRestaurant {
    private String restaurantName;
    private String location;
    private LocalTime openTime;
    private LocalTime closeTime;
    private List<MenuItem> menuItems = new ArrayList<>();

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

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    private MenuItem findItemByName(String itemName) {
        for (MenuItem item : menuItems) {
            if (item.getItemName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String itemName, int itemPrice) {
        MenuItem newItem = new MenuItem(itemName, itemPrice);
        menuItems.add(newItem);
    }

    public void removeFromMenu(String itemName) throws ItemNotFoundException {
        MenuItem itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new ItemNotFoundException(itemName);

        menuItems.remove(itemToBeRemoved);
    }

    public int calculateOrderValue(List<String> itemNames) throws ItemNotFoundException {
        int totalOrderValue = 0;

        for (String itemName : itemNames) {
            MenuItem item = findItemByName(itemName);
            if (item == null) {
                throw new ItemNotFoundException(itemName);
            }
            totalOrderValue += item.getItemPrice();
        }

        return totalOrderValue;
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
