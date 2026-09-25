# Pharmacy Management System

A console-based pharmacy management application written in Java. It uses CSV
files in the `data/` folder instead of a database or graphical user interface.

## Requirements

- Java Development Kit (JDK) 17 or newer
- A terminal or the VS Code integrated terminal

Check Java is installed:

```bash
java --version
javac --version
```

## Compile and Run

From the project root, run:

```bash
javac src/*.java
java -cp src Main
```

Alternatively, run from inside `src/`:

```bash
cd src
javac *.java
java Main
```

The application starts with a numbered menu. Enter `8` to exit. Data is loaded
from `data/` when the program starts and saved when the program exits or when a
record is changed.

## Menu Options

1. **Medicines**: add, view, search, update, and delete medicines.
2. **Categories**: add and view medicine categories.
3. **Suppliers**: add and view suppliers.
4. **Purchases**: record purchases and view purchase records.
5. **Sales**: record sales, apply discounts, print bills, and view sales.
6. **Stock**: view stock, add stock, and find low-stock medicines.
7. **Reports**: view stock, sales, purchase, and expiry reports.
8. **Exit**: save all data and close the application.

## Project Structure

```text
Pharmacy Management System/
├── src/
│   ├── Main.java
│   ├── PharmacySystem.java
│   ├── Medicine.java
│   ├── Category.java
│   ├── Supplier.java
│   ├── Purchase.java
│   ├── Sale.java
│   ├── FileManager.java
│   └── InputHelper.java
├── data/
│   ├── medicines.csv
│   ├── categories.csv
│   ├── suppliers.csv
│   ├── purchases.csv
│   └── sales.csv
└── README.md
```

## CSV Files

- `medicines.csv` stores medicine details, prices, quantities, expiry dates,
  category IDs, supplier IDs, and batch numbers.
- `categories.csv` stores category names and descriptions.
- `suppliers.csv` stores supplier contact information.
- `purchases.csv` stores purchase transactions.
- `sales.csv` stores sales and billing transactions.

Keep the CSV headers unchanged. Names and descriptions should not contain
commas because the application uses simple comma-separated parsing.

## Development Check

Compile with all compiler warnings enabled:

```bash
cd src
javac -Xlint:all *.java
```

No external libraries are required.