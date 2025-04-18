public class Electronics extends Product {

    public Electronics(String name, int quantity) {
        super(name, quantity);
    }

    @Override
    public void updateStock(int amount) {
        this.quantity += amount;
        System.out.println("Updated Electronics stock: " + name + ", New quantity: " + quantity);
    }
}
