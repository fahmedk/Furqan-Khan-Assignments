import java.io.*;
import java.util.Scanner;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class Main {
    private static final String FILE_PATH = "StudentRecords.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ensureFileExists();

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: searchStudentByRollNumber(); break;
                case 4: editStudent(); break;
                case 5: deleteStudent(); break;
                case 6: System.out.println("Exiting the program."); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private static void displayMenu() {
        System.out.println("\nStudent Information Management:");
        System.out.println("1. Add a new student record");
        System.out.println("2. View all student records");
        System.out.println("3. Search for a student record by roll number");
        System.out.println("4. Edit an existing student record");
        System.out.println("5. Delete a student record");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void ensureFileExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating the file.");
            }
        }
    }

    private static void addStudent() {
        try {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                throw new InvalidInputException("Name cannot be empty.");
            }

            System.out.print("Enter roll number: ");
            String rollNumber = scanner.nextLine();
            if (rollNumber.trim().isEmpty()) {
                throw new InvalidInputException("Roll number cannot be empty.");
            }

            System.out.print("Enter number of subjects: ");
            int numSubjects = scanner.nextInt();
            scanner.nextLine();
            if (numSubjects <= 0) {
                throw new InvalidInputException("Number of subjects should be greater than 0.");
            }

            double[] marks = new double[numSubjects];
            for (int i = 0; i < numSubjects; i++) {
                System.out.print("Enter mark for subject " + (i + 1) + ": ");
                marks[i] = scanner.nextDouble();
                if (marks[i] < 0 || marks[i] > 100) {
                    throw new InvalidInputException("Marks should be between 0 and 100.");
                }
            }

            saveStudentToFile(name, rollNumber, marks);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void saveStudentToFile(String name, String rollNumber, double[] marks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(name + "," + rollNumber);
            for (double mark : marks) {
                writer.write("," + mark);
            }
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    private static void viewAllStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
    }

    private static void searchStudentByRollNumber() {
        System.out.print("Enter roll number to search: ");
        String rollNumber = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains(rollNumber)) {
                    System.out.println("Student found: " + line);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("No student found with the roll number: " + rollNumber);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
    }

    private static void editStudent() {
        System.out.print("Enter roll number to edit: ");
        String rollNumber = scanner.nextLine();

        StringBuilder updatedContent = new StringBuilder();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(rollNumber)) {
                    found = true;
                    System.out.println("Editing student: " + line);
                    System.out.print("Enter new name (press Enter to skip): ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new roll number (press Enter to skip): ");
                    String newRollNumber = scanner.nextLine();

                    if (!newName.isEmpty()) {
                        line = line.replaceFirst("^[^,]+", newName);
                    }
                    if (!newRollNumber.isEmpty()) {
                        line = line.replaceFirst(",[^,]+", "," + newRollNumber);
                    }
                }
                updatedContent.append(line).append("\n");
            }

            if (!found) {
                System.out.println("No student found with the roll number: " + rollNumber);
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                writer.write(updatedContent.toString());
            }

        } catch (IOException e) {
            System.out.println("Error editing student.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter roll number to delete: ");
        String rollNumber = scanner.nextLine();

        StringBuilder updatedContent = new StringBuilder();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].trim().equals(rollNumber.trim())) {
                    found = true;
                    continue; // skip this line
                }
                updatedContent.append(line).append("\n");
            }

            if (!found) {
                System.out.println("No student found with the roll number: " + rollNumber);
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                writer.write(updatedContent.toString());
            }

            System.out.println("Student record with roll number " + rollNumber + " deleted successfully.");

        } catch (IOException e) {
            System.out.println("Error deleting student.");
        }
    }

}
