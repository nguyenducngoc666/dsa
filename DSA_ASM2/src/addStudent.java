import java.util.InputMismatchException;
import java.util.Scanner;

private static void addStudent(Scanner scanner) {
    try {
        System.out.print("Enter student ID: ");
        int id = getValidIntegerInput(scanner);
        if (id <= 0) {
            System.out.println("Invalid ID. ID must be a positive number.");
            return;
        }

        scanner.nextLine(); // Consume newline character
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Invalid Name. Name cannot be empty.");
            return;
        }

        System.out.print("Enter student marks: ");
        double marks = getValidDoubleInput(scanner);
        if (marks < 0 || marks > 10) {
            System.out.println("Invalid Marks. Marks must be between 0 and 10.");
            return;
        }

        Student student = new Student(id, name, marks);
        Stack.push(student);
        System.out.println("Student added successfully.");
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter valid data.");
        scanner.nextLine(); // Clear invalid input
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while adding the student: " + e.getMessage());
    }
}

private static void editStudent(Scanner scanner) {
    try {
        System.out.print("Enter student ID to edit: ");
        int id = getValidIntegerInput(scanner);
        if (id <= 0) {
            System.out.println("Invalid ID. ID must be a positive number.");
            return;
        }

        scanner.nextLine(); // Consume newline character
        System.out.print("Enter new student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Invalid Name. Name cannot be empty.");
            return;
        }

        System.out.print("Enter new student marks: ");
        double marks = getValidDoubleInput(scanner);
        if (marks < 0 || marks > 10) {
            System.out.println("Invalid Marks. Marks must be between 0 and 10.");
            return;
        }

        Stack.editStudent(id, name, marks);
        System.out.println("Student updated successfully.");
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter valid data.");
        scanner.nextLine(); // Clear invalid input
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while editing the student: " + e.getMessage());
    }
}

private static void deleteStudent(Scanner scanner) {
    try {
        System.out.print("Enter student ID to delete: ");
        int id = getValidIntegerInput(scanner);
        if (id <= 0) {
            System.out.println("Invalid ID. ID must be a positive number.");
            return;
        }

        Stack.deleteStudent(id);
        System.out.println("Student deleted successfully.");
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid integer ID.");
        scanner.nextLine(); // Clear invalid input
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while deleting the student: " + e.getMessage());
    }
}

private static void searchStudent(Scanner scanner) {
    try {
        System.out.print("Enter student ID to search: ");
        int id = getValidIntegerInput(scanner);
        if (id <= 0) {
            System.out.println("Invalid ID. ID must be a positive number.");
            return;
        }

        Student foundStudent = Stack.searchStudent(id);
        if (foundStudent != null) {
            System.out.printf("Found: ID: %d, Name: %s, Marks: %.2f, Rank: %s%n",
                    foundStudent.getId(), foundStudent.getName(), foundStudent.getMarks(), foundStudent.getRank());
        } else {
            System.out.println("No student found with the given ID.");
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid integer ID.");
        scanner.nextLine(); // Clear invalid input
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while searching for the student: " + e.getMessage());
    }
}

private static int getValidIntegerInput(Scanner scanner) {
    while (true) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.print("Invalid input. Please enter a valid integer: ");
            scanner.nextLine(); // Clear invalid input
        }
    }
}

private static double getValidDoubleInput(Scanner scanner) {
    while (true) {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.print("Invalid input. Please enter a valid number: ");
            scanner.nextLine(); // Clear invalid input
        }
    }
}

public void main() {
}
