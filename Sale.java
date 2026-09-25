public class Sale {

    // Variables
    private final int saleId;
    private final int medicineId;
    private final int quantity;
    private final double sellingPrice;
    private final double discount;
    private final String date;
    private final String customerName;

    // Constructor
    public Sale(int saleId, int medicineId, int quantity,
                double sellingPrice, double discount,
                String date, String customerName) {

        this.saleId = saleId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.discount = discount;
        this.date = date;
        this.customerName = customerName;
    }

    // Getters
    public int getSaleId() {
        return saleId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    // Calculate gross amount
    public double getGrossAmount() {
        return quantity * sellingPrice;
    }

    // Calculate net amount
    public double getNetAmount() {
        return getGrossAmount() - discount;
    }

    // Display sale information
    public void display() {
        System.out.println("----------------------------------------");
        System.out.println("Sale ID: " + saleId);
        System.out.println("Medicine ID: " + medicineId);
        System.out.println("Quantity: " + quantity);
        System.out.println("Selling Price: Rs. " + sellingPrice);
        System.out.println("Gross Amount: Rs. " + getGrossAmount());
        System.out.println("Discount: Rs. " + discount);
        System.out.println("Net Amount: Rs. " + getNetAmount());
        System.out.println("Date: " + date);
        System.out.println("Customer Name: " + customerName);
        System.out.println("----------------------------------------");
    }

    // Convert sale information into CSV format
    public String toCSV() {
        return saleId + "," +
               medicineId + "," +
               quantity + "," +
               sellingPrice + "," +
               discount + "," +
               date + "," +
               customerName;
    }

    // Create Sale object from CSV data
    public static Sale fromCSV(String line) {

        String[] data = line.split(",");

        return new Sale(
            Integer.parseInt(data[0]),
            Integer.parseInt(data[1]),
            Integer.parseInt(data[2]),
            Double.parseDouble(data[3]),
            Double.parseDouble(data[4]),
            data[5],
            data[6]
        );
    }
}