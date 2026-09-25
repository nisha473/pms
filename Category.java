public class Category {

    private final int id;
    private String name;
    private String description;

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void display() {
        System.out.println("--------------------------------");
        System.out.println("Category ID: " + id);
        System.out.println("Category Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("--------------------------------");
    }

    public String toCSV() {
        return id + "," + name + "," + description;
    }

    public static Category fromCSV(String line) {
        String[] data = line.split(",");

        return new Category(
            Integer.parseInt(data[0]),
            data[1],
            data[2]
        );
    }
}