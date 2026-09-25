import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static File resolveDataFolder() {
        File currentFolder = new File("data");
        File parentFolder = new File("../data");

        if (new File(System.getProperty("user.dir")).getName().equals("src")
                && parentFolder.exists()) {
            return parentFolder;
        }

        return currentFolder;
    }

    private static File resolveFile(String fileName) {
        String dataPrefix = "data" + File.separator;

        if (fileName.startsWith(dataPrefix)) {
            return new File(resolveDataFolder(),
                    fileName.substring(dataPrefix.length()));
        }

        return new File(fileName);
    }

    // Create data folder if it does not exist
    public static void createDataFolder() {
        File folder = resolveDataFolder();

        if (!folder.exists()) {
            folder.mkdir();
            System.out.println("Data folder created.");
        }
    }

    // Read all lines from a file
    public static List<String> readFile(String fileName) {

        List<String> lines = new ArrayList<>();

        try {
            File file = resolveFile(fileName);

            if (!file.exists()) {
                return lines;
            }

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
            System.out.println(e.getMessage());
        }

        return lines;
    }

    // Write data to a file
    public static void writeFile(String fileName, List<String> lines) {

        try {
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(resolveFile(fileName)));

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing file: " + fileName);
            System.out.println(e.getMessage());
        }
    }

    // Add one new line to an existing file
    public static void appendToFile(String fileName, String line) {

        try {
            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(resolveFile(fileName), true)
                    );

            writer.write(line);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            System.out.println("Error adding data to file: " + fileName);
            System.out.println(e.getMessage());
        }
    }

    // Create a backup of a file
    public static void backupFile(String fileName) {

        File originalFile = resolveFile(fileName);

        if (!originalFile.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        File backupFile = new File(originalFile.getPath() + ".backup");

        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(originalFile));

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(backupFile));

            String line;

            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            System.out.println("Backup created: " + backupFile);

        } catch (IOException e) {
            System.out.println("Error creating backup.");
            System.out.println(e.getMessage());
        }
    }

    // Check whether a file exists
    public static boolean fileExists(String fileName) {

        File file = resolveFile(fileName);

        return file.exists();
    }
}