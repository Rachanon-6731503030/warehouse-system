import java.io.*;
import java.util.*;

public class WarehouseManagementSystem {

    private static Map<String, Product> warehouse = new HashMap<>();
    private static final String FILE_NAME = "warehouse.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadFromFile();
    
        while (true) {
            System.out.println("\n--- Warehouse Management ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Sell Product");
            System.out.println("4. Show Products");
            System.out.println("5. Save & Exit");
            System.out.println("6. Reset Warehouse");
            System.out.print("Choose: ");
    
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number (1-6).");
                    continue;
                }
    
            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    removeProduct(scanner);
                    break;
                case 3:
                    sellProduct(scanner);
                    break;
                case 4:
                    showProducts();
                    break;
                case 5:
                    saveToFile();
                    System.out.println("Saved! Goodbye!");
                    System.exit(0);
                    break;
                case 6:
                    resetFileWithConfirm(scanner);
                    break;
                default:
                    System.out.println("Invalid choice! Please choose between 1 and 6.");
            }
        }
    }

    private static void addProduct(Scanner scanner) {
        System.out.print("Enter product type 1(Electronics) / 2(FoodProduct): ");
        String type = scanner.nextLine();
    
        System.out.print("Enter product Name / product ID: ");
        String name = scanner.nextLine();
    
        int quantity;
        while (true) {
            System.out.print("Enter quantity (positive number only): ");
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity <= 0) {
                    System.out.println("Quantity must be greater than zero!!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    
        Product product;
        if (type.equals("1")) {
            product = new Electronics(name, quantity);
        } else if (type.equals("2")) {
            product = new FoodProduct(name, quantity);
        } else {
            System.out.println("Invalid product type!");
            return;
        }
    
        warehouse.put(name, product);
        System.out.println("Product added!");
    }

    private static void removeProduct(Scanner scanner) {
        System.out.print("Enter product name to remove: ");
        String name = scanner.nextLine();
        if (warehouse.containsKey(name)) {
            warehouse.remove(name);
            System.out.println("Product removed!");
        } else {
            System.out.println("Product not found!");
        }
    }

    private static void sellProduct(Scanner scanner) {
        System.out.print("Enter product name to sell: ");
        String name = scanner.nextLine();
    
        if (!warehouse.containsKey(name)) {
            System.out.println("Product not found!");
            return;
        }
    
        Product product = warehouse.get(name);
    
        int sellQuantity;
        while (true) {
            System.out.print("Enter quantity to sell: ");
            try {
                sellQuantity = Integer.parseInt(scanner.nextLine());
                if (sellQuantity <= 0) {
                    System.out.println("Quantity must be a positive number!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    
        try {
            if (product.getQuantity() < sellQuantity) {
                throw new OutOfStockException("Not enough stock! Available: " + product.getQuantity());
            }
            product.updateStock(-sellQuantity);
            System.out.println("Sold " + sellQuantity + " unit(s) of " + product.getName());
        } catch (OutOfStockException e) {
            System.out.println(e.getMessage());
        }
    }
    

    private static void showProducts() {
        if (warehouse.isEmpty()) {
            System.out.println("No products in warehouse.");
        } else {
            for (Product product : warehouse.values()) {
                System.out.println(product.getName() + " | Quantity: " + product.getQuantity());
            }
        }
    }

    private static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Product product : warehouse.values()) {
                writer.println(product.getClass().getSimpleName() + "," + product.getName() + "," + product.getQuantity());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                if (type.equalsIgnoreCase("Electronics")) {
                    warehouse.put(name, new Electronics(name, quantity));
                } else if (type.equalsIgnoreCase("FoodProduct")) {
                    warehouse.put(name, new FoodProduct(name, quantity));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void resetFileWithConfirm(Scanner scanner) {
        System.out.print("Are you sure you want to reset the warehouse? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
    
        if (confirm.equals("y") || confirm.equals("yes")) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
                writer.print("");
                warehouse.clear();
                System.out.println("Warehouse has been reset.");
            } catch (IOException e) {
                System.out.println("Error resetting file: " + e.getMessage());
            }
        } else {
            System.out.println("Reset cancelled.");
        }
    }
}