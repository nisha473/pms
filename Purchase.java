public class Purchase {

    // Variables
    private final int purchaseId;
    private final int medicineId;
    private final int supplierId;
    private final int quantity;
    private final double unitCost;
    private final String purchaseDate;
    private final String invoiceNumber;

    // Constructor
    public Purchase(int purchaseId, int medicineId, int supplierId,
                    int quantity, double unitCost,
                    String purchaseDate, String invoiceNumber) {

        this.purchaseId = purchaseId;
        this.medicineId = medicineId;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.purchaseDate = purchaseDate;
        this.invoiceNumber = invoiceNumber;
    }

    // Getters
    public int getPurchaseId() {
        return purchaseId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    // Calculate total cost
    public double getTotalCost() {
        return quantity * unitCost;
    }

    // Display purchase information
    public void display() {
        System.out.println("----------------------------------------");
        System.out.println("Purchase ID: " + purchaseId);
        System.out.println("Medicine ID: " + medicineId);
        System.out.println("Supplier ID: " + supplierId);
        System.out.println("Quantity: " + quantity);
        System.out.println("Unit Cost: Rs. " + unitCost);
        System.out.println("Purchase Date: " + purchaseDate);
        System.out.println("Invoice Number: " + invoiceNumber);
        System.out.println("Total Cost: Rs. " + getTotalCost());
        System.out.println("----------------------------------------");
    }

    // Convert purchase information into CSV format
    public String toCSV() {
        return purchaseId + "," +
               medicineId + "," +
               supplierId + "," +
               quantity + "," +
               unitCost + "," +
               purchaseDate + "," +
               invoiceNumber;
    }

    // Create Purchase object from CSV data
    public static Purchase fromCSV(String line) {

        String[] data = line.split(",");

        return new Purchase(
            Integer.parseInt(data[0]),
            Integer.parseInt(data[1]),
            Integer.parseInt(data[2]),
            Integer.parseInt(data[3]),
            Double.parseDouble(data[4]),
            data[5],
            data[6]
        );
    }
}