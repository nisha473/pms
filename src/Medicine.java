public class Medicine {
    private final int id;
    private String name;
    private final int categoryId;
    private final int supplierId;
    private double purchasePrice;
    private double sellingPrice;
    private int quantity;
    private String expiryDate;
    private final String batchNo;

    public Medicine(int id, String name, int categoryId, int supplierId,
                    double purchasePrice, double sellingPrice, int quantity,
                    String expiryDate, String batchNo) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.batchNo = batchNo;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() { return quantity; }
    public double getSellingPrice() { return sellingPrice; }
    public String getExpiryDate() { return expiryDate; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchasePrice(double price) {
        this.purchasePrice = price;
    }

    public void setSellingPrice(double price) {
        this.sellingPrice = price;
    }

    public void setExpiryDate(String date) {
        this.expiryDate = date;
    }

    public void addStock(int amount) {
        quantity += amount;
    }

    public void removeStock(int amount) {
        quantity -= amount;
    }

    public void display() {
        String details = id + " | " + name
                + " | Stock: " + quantity
                + " | Buy: Rs." + purchasePrice
                + " | Sell: Rs." + sellingPrice
                + " | Expiry: " + expiryDate
                + " | Batch: " + batchNo;
        System.out.println(details);
    }

    public String toCSV() {
        return id + "," + name + "," + categoryId + "," +
               supplierId + "," + purchasePrice + "," +
               sellingPrice + "," + quantity + "," +
               expiryDate + "," + batchNo;
    }

    public static Medicine fromCSV(String s) {
        String[] p = s.split(",", -1);

        return new Medicine(
            Integer.parseInt(p[0]),
            p[1],
            Integer.parseInt(p[2]),
            Integer.parseInt(p[3]),
            Double.parseDouble(p[4]),
            Double.parseDouble(p[5]),
            Integer.parseInt(p[6]),
            p[7],
            p[8]
        );
    }
}