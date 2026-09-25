import java.util.ArrayList;
import java.util.List;

public class PharmacySystem {

    private final List<Medicine> medicines = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();
    private final List<Supplier> suppliers = new ArrayList<>();
    private final List<Purchase> purchases = new ArrayList<>();
    private final List<Sale> sales = new ArrayList<>();

    public PharmacySystem() {
        FileManager.createDataFolder();
        loadData();
    }

    public void start() {

        boolean run = true;

        while (run) {

            System.out.println("\n===== PHARMACY MANAGEMENT SYSTEM =====");
            System.out.println("1. Medicines");
            System.out.println("2. Categories");
            System.out.println("3. Suppliers");
            System.out.println("4. Purchases");
            System.out.println("5. Sales");
            System.out.println("6. Stock");
            System.out.println("7. Reports");
            System.out.println("8. Exit");

            int choice = InputHelper.readInt("Enter choice: ");

            switch (choice) {
                case 1 -> medicineMenu();
                case 2 -> categoryMenu();
                case 3 -> supplierMenu();
                case 4 -> purchaseMenu();
                case 5 -> saleMenu();
                case 6 -> stockMenu();
                case 7 -> reportMenu();
                case 8 -> run = false;
                default -> System.out.println("Invalid choice.");
            }
        }

        saveData();
        System.out.println("Thank you!");
    }

    // ================= MEDICINE =================

    private void medicineMenu() {

        System.out.println("\n--- Medicine ---");
        System.out.println("1. Add");
        System.out.println("2. View");
        System.out.println("3. Search");
        System.out.println("4. Update");
        System.out.println("5. Delete");

        int c = InputHelper.readInt("Choice: ");

        switch (c) {
            case 1 -> addMedicine();
            case 2 -> viewMedicines();
            case 3 -> searchMedicine();
            case 4 -> updateMedicine();
            case 5 -> deleteMedicine();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addMedicine() {

        int id = medicines.size() + 1;

        String name = InputHelper.readRequiredString("Name: ");
        int category = InputHelper.readPositiveInt("Category ID: ");
        int supplier = InputHelper.readPositiveInt("Supplier ID: ");
        double buy = InputHelper.readPositiveDouble("Purchase price: ");
        double sell = InputHelper.readPositiveDouble("Selling price: ");
        int quantity = InputHelper.readPositiveInt("Quantity: ");
        String expiry = InputHelper.readDate("Expiry YYYY-MM-DD: ");
        String batch = InputHelper.readRequiredString("Batch No: ");

        medicines.add(new Medicine(
                id, name, category, supplier,
                buy, sell, quantity, expiry, batch
        ));

        saveData();
        System.out.println("Medicine added.");
    }

    private void viewMedicines() {

        if (medicines.isEmpty()) {
            System.out.println("No medicines.");
            return;
        }

        for (Medicine m : medicines) {
            m.display();
        }
    }

    private void searchMedicine() {

        String name = InputHelper.readRequiredString("Search name: ");

        boolean found = false;

        for (Medicine m : medicines) {

            if (m.getName().toLowerCase()
                    .contains(name.toLowerCase())) {

                m.display();
                found = true;
            }
        }

        if (!found)
            System.out.println("Medicine not found.");
    }

    private void updateMedicine() {

        int id = InputHelper.readPositiveInt("Medicine ID: ");
        Medicine m = findMedicine(id);

        if (m == null) {
            System.out.println("Medicine not found.");
            return;
        }

        m.setName(InputHelper.readRequiredString("New name: "));
        m.setPurchasePrice(
                InputHelper.readPositiveDouble("New purchase price: ")
        );
        m.setSellingPrice(
                InputHelper.readPositiveDouble("New selling price: ")
        );
        m.setExpiryDate(
                InputHelper.readDate("New expiry date: ")
        );

        saveData();
        System.out.println("Medicine updated.");
    }

    private void deleteMedicine() {

        int id = InputHelper.readPositiveInt("Medicine ID: ");
        Medicine m = findMedicine(id);

        if (m != null) {
            medicines.remove(m);
            saveData();
            System.out.println("Medicine deleted.");
        } else {
            System.out.println("Medicine not found.");
        }
    }

    // ================= CATEGORY =================

    private void categoryMenu() {

        System.out.println("\n--- Category ---");
        System.out.println("1. Add");
        System.out.println("2. View");

        int c = InputHelper.readInt("Choice: ");

        switch (c) {
            case 1 -> addCategory();
            case 2 -> viewCategories();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addCategory() {

        int id = categories.size() + 1;

        String name =
                InputHelper.readRequiredString("Category name: ");

        String description =
                InputHelper.readRequiredString("Description: ");

        categories.add(
                new Category(id, name, description)
        );

        saveData();

        System.out.println("Category added.");
    }

    private void viewCategories() {

        for (Category c : categories)
            c.display();
    }

    // ================= SUPPLIER =================

    private void supplierMenu() {

        System.out.println("\n--- Supplier ---");
        System.out.println("1. Add");
        System.out.println("2. View");

        int c = InputHelper.readInt("Choice: ");

        switch (c) {
            case 1 -> addSupplier();
            case 2 -> viewSuppliers();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addSupplier() {

        int id = 100 + suppliers.size() + 1;

        String name =
                InputHelper.readRequiredString("Supplier name: ");

        String phone =
                InputHelper.readRequiredString("Phone: ");

        String address =
                InputHelper.readRequiredString("Address: ");

        String email =
                InputHelper.readRequiredString("Email: ");

        suppliers.add(
                new Supplier(id, name, phone, address, email)
        );

        saveData();

        System.out.println("Supplier added.");
    }

    private void viewSuppliers() {

        for (Supplier s : suppliers)
            s.display();
    }

    // ================= PURCHASE =================

    private void purchaseMenu() {

        System.out.println("\n--- Purchase ---");
        System.out.println("1. Add Purchase");
        System.out.println("2. View Purchases");

        int c = InputHelper.readInt("Choice: ");

        if (c == 1) addPurchase();
        else if (c == 2) viewPurchases();
    }

    private void addPurchase() {

        int id = 1000 + purchases.size() + 1;

        int medicineId =
                InputHelper.readPositiveInt("Medicine ID: ");

        Medicine m = findMedicine(medicineId);

        if (m == null) {
            System.out.println("Medicine not found.");
            return;
        }

        int supplier =
                InputHelper.readPositiveInt("Supplier ID: ");

        int quantity =
                InputHelper.readPositiveInt("Quantity: ");

        double cost =
                InputHelper.readPositiveDouble("Unit cost: ");

        String date =
                InputHelper.readDate("Date YYYY-MM-DD: ");

        String invoice =
                InputHelper.readRequiredString("Invoice: ");

        purchases.add(
                new Purchase(
                        id, medicineId, supplier,
                        quantity, cost, date, invoice
                )
        );

        m.addStock(quantity);

        saveData();

        System.out.println("Purchase added.");
        System.out.println("Total: Rs. " +
                (quantity * cost));
    }

    private void viewPurchases() {

        for (Purchase p : purchases)
            p.display();
    }

    // ================= SALES =================

    private void saleMenu() {

        System.out.println("\n--- Sales ---");
        System.out.println("1. New Sale");
        System.out.println("2. View Sales");

        int c = InputHelper.readInt("Choice: ");

        if (c == 1) addSale();
        else if (c == 2) viewSales();
    }

    private void addSale() {

        int id = 2000 + sales.size() + 1;

        int medicineId =
                InputHelper.readPositiveInt("Medicine ID: ");

        Medicine m = findMedicine(medicineId);

        if (m == null) {
            System.out.println("Medicine not found.");
            return;
        }

        int quantity =
                InputHelper.readPositiveInt("Quantity: ");

        if (quantity > m.getQuantity()) {
            System.out.println("Not enough stock.");
            return;
        }

        double discount =
                InputHelper.readNonNegativeDouble("Discount: ");

        String date =
                InputHelper.readDate("Date YYYY-MM-DD: ");

        String customer =
                InputHelper.readRequiredString("Customer name: ");

        Sale sale = new Sale(
                id,
                medicineId,
                quantity,
                m.getSellingPrice(),
                discount,
                date,
                customer
        );

        sales.add(sale);
        m.removeStock(quantity);

        saveData();

        System.out.println("\n===== BILL =====");
        System.out.println("Customer: " + customer);
        System.out.println("Medicine: " + m.getName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Gross: Rs. " + sale.getGrossAmount());
        System.out.println("Discount: Rs. " + discount);
        System.out.println("Total: Rs. " + sale.getNetAmount());
    }

    private void viewSales() {

        for (Sale s : sales)
            s.display();
    }

    // ================= STOCK =================

    private void stockMenu() {

        System.out.println("\n--- Stock ---");
        System.out.println("1. View Stock");
        System.out.println("2. Add Stock");
        System.out.println("3. Low Stock");

        int c = InputHelper.readInt("Choice: ");

        switch (c) {
            case 1 -> viewStock();
            case 2 -> addStock();
            case 3 -> lowStock();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void viewStock() {

        for (Medicine m : medicines) {

            System.out.println(
                    m.getId() + " - " +
                    m.getName() +
                    " : " + m.getQuantity()
            );
        }
    }

    private void addStock() {

        int id = InputHelper.readPositiveInt("Medicine ID: ");
        Medicine m = findMedicine(id);

        if (m == null) {
            System.out.println("Medicine not found.");
            return;
        }

        int quantity =
                InputHelper.readPositiveInt("Quantity: ");

        m.addStock(quantity);
        saveData();

        System.out.println("Stock updated.");
    }

    private void lowStock() {

        int limit =
                InputHelper.readPositiveInt("Low stock limit: ");

        for (Medicine m : medicines) {

            if (m.getQuantity() <= limit) {

                System.out.println(
                        m.getName() +
                        " - Stock: " +
                        m.getQuantity()
                );
            }
        }
    }

    // ================= REPORT =================

    private void reportMenu() {

        System.out.println("\n--- Reports ---");
        System.out.println("1. Stock Report");
        System.out.println("2. Sales Report");
        System.out.println("3. Purchase Report");
        System.out.println("4. Expiry Report");

        int c = InputHelper.readInt("Choice: ");

        switch (c) {
            case 1 -> viewStock();
            case 2 -> salesReport();
            case 3 -> purchaseReport();
            case 4 -> expiryReport();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void salesReport() {

        double total = 0;

        for (Sale s : sales)
            total += s.getNetAmount();

        System.out.println(
                "Total Sales: Rs. " + total
        );
    }

    private void purchaseReport() {

        double total = 0;

        for (Purchase p : purchases)
            total += p.getTotalCost();

        System.out.println(
                "Total Purchases: Rs. " + total
        );
    }

    private void expiryReport() {

        for (Medicine m : medicines) {

            System.out.println(
                    m.getName() +
                    " - Expiry: " +
                    m.getExpiryDate()
            );
        }
    }

    // ================= FIND =================

    private Medicine findMedicine(int id) {

        for (Medicine m : medicines) {

            if (m.getId() == id)
                return m;
        }

        return null;
    }

    // ================= FILE =================

    private void loadData() {

        for (String s :
                FileManager.readFile("data/medicines.csv")) {

            if (!s.startsWith("id,"))
                medicines.add(Medicine.fromCSV(s));
        }

        for (String s :
                FileManager.readFile("data/categories.csv")) {

            if (!s.startsWith("id,"))
                categories.add(Category.fromCSV(s));
        }

        for (String s :
                FileManager.readFile("data/suppliers.csv")) {

            if (!s.startsWith("id,"))
                suppliers.add(Supplier.fromCSV(s));
        }

        for (String s :
                FileManager.readFile("data/purchases.csv")) {

            if (!s.startsWith("purchaseId,"))
                purchases.add(Purchase.fromCSV(s));
        }

        for (String s :
                FileManager.readFile("data/sales.csv")) {

            if (!s.startsWith("saleId,"))
                sales.add(Sale.fromCSV(s));
        }
    }

    private void saveData() {

        saveMedicines();
        saveCategories();
        saveSuppliers();
        savePurchases();
        saveSales();
    }

    private void saveMedicines() {

        List<String> data = new ArrayList<>();

        data.add(
                "id,name,categoryId,supplierId,purchasePrice," +
                "sellingPrice,quantity,expiryDate,batchNo"
        );

        for (Medicine m : medicines)
            data.add(m.toCSV());

        FileManager.writeFile(
                "data/medicines.csv", data
        );
    }

    private void saveCategories() {

        List<String> data = new ArrayList<>();

        data.add("id,name,description");

        for (Category c : categories)
            data.add(c.toCSV());

        FileManager.writeFile(
                "data/categories.csv", data
        );
    }

    private void saveSuppliers() {

        List<String> data = new ArrayList<>();

        data.add("id,name,phone,address,email");

        for (Supplier s : suppliers)
            data.add(s.toCSV());

        FileManager.writeFile(
                "data/suppliers.csv", data
        );
    }

    private void savePurchases() {

        List<String> data = new ArrayList<>();

        data.add(
                "purchaseId,medicineId,supplierId," +
                "quantity,unitCost,purchaseDate,invoiceNumber"
        );

        for (Purchase p : purchases)
            data.add(p.toCSV());

        FileManager.writeFile(
                "data/purchases.csv", data
        );
    }

    private void saveSales() {

        List<String> data = new ArrayList<>();

        data.add(
                "saleId,medicineId,quantity," +
                "sellingPrice,discount,date,customerName"
        );

        for (Sale s : sales)
            data.add(s.toCSV());

        FileManager.writeFile(
                "data/sales.csv", data
        );
    }
}