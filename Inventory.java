class Inventory {
    private String itemName;
    private int itemId;
    private int stockCount;
    private int minimumStock;

    public Inventory(String itemName, int itemId, int stockCount, int minimumStock) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.stockCount = stockCount;
        this.minimumStock = minimumStock;
    }

    public int getItemId() {
        return itemId;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void updateStock(int amount) {
        this.stockCount += amount;
    }

    public boolean isBelowMinimum() {
        return stockCount < minimumStock;
    }

    @Override
    public String toString() {
        return "Sklad [Položka: " + itemName + ", ID: " + itemId + ", Zásoby: " + stockCount + ", Minimum: " + minimumStock + "]";
    }
}
