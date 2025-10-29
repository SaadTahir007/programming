public class CommercialMarket {

    private String marketName;
    private Shop[] shops;

    public CommercialMarket(String name, int totalShops) {
        this.marketName = name;
        this.shops = new Shop[totalShops];

        for (int i = 0; i < shops.length; i++) {
            String id = String.format("%s-%02d", name, i + 1);
            double price = 2500000 + (i * 100000);
            shops[i] = new Shop(id, price);
        }
    }


	public boolean bookShop(String id) {
    	    for (Shop s : shops)
               if (s.getShopID().equals(id))
                  return s.bookShop();
        return false;
    }

	public boolean cancelShop(String id) {
    	    for (Shop s : shops)
               if (s.getShopID().equals(id))
                   return s.cancelShop();
        return false;
   }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commercial Market: ").append(marketName).append("\n");
        for (Shop shop : shops) {
            sb.append("   ").append(shop.toString()).append("\n");
        }
        return sb.toString();
    }
}
