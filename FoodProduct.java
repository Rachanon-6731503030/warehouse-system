public class FoodProduct extends Product {

    public FoodProduct(String name, int quantity) {
        super(name, quantity);
    }

    @Override
    public void updateStock(int amount) {
        this.quantity += amount;
        System.out.println("Updated FoodProduct stock: " + name + ", New quantity: " + quantity);
    }
}
