public class Shop {

    private String shopID;
    private double price;

    public Shop(String id, double price) {
        this.shopID = id;
        this.price = price;
    }

private boolean available = true;

public boolean bookShop() {
    if (!available) return false;
    available = false;
    return true;
}

public boolean cancelShop() {
    if (available) return false;
    available = true;
    return true;
}

public String getShopID() {
    return shopID;
}


    @Override
    public String toString() {
        return "Shop " + shopID + " - " + price + " PKR";
    }
}
