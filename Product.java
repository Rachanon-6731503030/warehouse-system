public abstract class Product implements StockManageable {
    protected String name;
    protected int quantity;

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract void updateStock(int amount);
}