public class Supplier {
    private final int id;
    private final String name;
    private final String phone;
    private final String address;
    private final String email;

    public Supplier(int id, String name, String phone,
                    String address, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public void display() {
        System.out.println(
            id + " | " + name + " | " +
            phone + " | " + address + " | " + email
        );
    }

    public String toCSV() {
        return id + "," + name + "," + phone + "," +
               address + "," + email;
    }

    public static Supplier fromCSV(String s) {
        String[] values = s.split(",", -1);

        return new Supplier(
            Integer.parseInt(values[0]),
            values[1],
            values[2],
            values[3],
            values[4]
        );
    }
}