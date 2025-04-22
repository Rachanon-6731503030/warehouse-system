# warehouse-system
A simple Java-based warehouse management system that allows product tracking, stock updates, and inventory reporting using file I/O, OOP, and exception handling.

# Project Objective
This program was developed to help manage a small warehouse, allowing users to add, remove, sell, and display products, as well as save data to a file for persistence.

# System Overview
The main logic is inside the WarehouseManagementSystem class which provides a user-friendly menu via the command line. All products are stored in a HashMap, and the data is saved to or loaded from a file named warehouse.txt.

The system supports two types of products: Electronics and FoodProduct.

# Code Explanation: Small Warehouse Management System
1. WarehouseManagementSystem (Main Class)
This is the main class of the program. It provides a menu-driven interface to manage the warehouse.

* Main features:

* Add product

* Remove product

* Sell product

* Show all products

* Save data to a file and exit

* Reset warehouse data

Important methods:
* addProduct() – Adds either an Electronics or FoodProduct based on user input, including name and quantity.

* removeProduct() – Removes a product by its name.

* sellProduct() – Reduces product quantity. If the stock is insufficient, it throws a custom OutOfStockException.

* showProducts() – Displays all products currently in the warehouse.

* saveToFile() – Saves the current product list to a file (warehouse.txt).

* loadFromFile() – Loads saved products from a file on startup.

* resetFileWithConfirm() – Clears the warehouse and file after user confirmation.

2. Product (Abstract Class)
This is the base class for all product types.

Attributes:

* name – Name of the product

* quantity – Stock quantity

Abstract Method:

* updateStock(int amount) – Updates product stock (positive or negative)

3. StockManageable (Interface)
This interface enforces that any product type must implement the updateStock() method to manage its stock.

4. Electronics & FoodProduct (Subclasses of Product)
These are concrete product types that override the updateStock() method to update and print stock changes accordingly.

5. OutOfStockException (Custom Exception)
Thrown when trying to sell more than the available stock. Helps handle errors gracefully.


