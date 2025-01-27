import java.io.*;
import java.util.Scanner;

public class FileManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the file name (with extension): ");
        String fileName = scanner.nextLine();

        System.out.println("Choose an operation:");
        System.out.println("1. Create a file");
        System.out.println("2. Write to the file");
        System.out.println("3. Read from the file");
        System.out.println("4. Delete the file");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        switch (choice) {
            case 1:
                createFile(fileName);
                break;
            case 2:
                System.out.println("Enter content to write into the file:");
                String content = scanner.nextLine();
                writeFile(fileName, content);
                break;
            case 3:
                readFile(fileName);
                break;
            case 4:
                deleteFile(fileName);
                break;
            case 5:
                System.out.println("Enter content to append to the file:");
                String contentToAppend = scanner.nextLine();
                appendToFile(fileName, contentToAppend);
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }

    public static void createFile(String fileName) {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    public static void appendToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName, true)) { // "true" enables append mode
            writer.write(content);
            System.out.println("Successfully appended to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while appending to the file.");
            e.printStackTrace();
        }
    }

    public static void writeFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static void readFile(String fileName) {
        File file = new File(fileName);
        try (Scanner reader = new Scanner(file)) {
            System.out.println("Reading from the file:");
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}
