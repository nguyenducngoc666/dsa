import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Stack studentStack;
    private static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the stack for managing students
        System.out.print("Specify the number of students to manage: ");
        int numberOfStudents = scanner.nextInt();
        studentStack = new Stack(numberOfStudents);

        // Main program loop
        while (true) {
            try {
                displayMenu();
                System.out.print("Select an option by entering the corresponding number: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        editStudent(scanner);
                        break;
                    case 3:
                        deleteStudent(scanner);
                        break;
                    case 4:
                        searchStudent(scanner);
                        break;
                    case 5:
                        studentStack.sortStudentsQuick();
                        break;
                    case 6:
                        studentStack.sortStudentsBubble();
                        break;
                    case 7:
                        studentStack.displayStudents();
                        break;
                    case 8:
                        generateRandomStudents(scanner);
                        break;
                    case 9:
                        System.out.println("Closing the program. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Option not recognized. Please select a valid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input error! Ensure you enter a numeric value.");
                scanner.next(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An unexpected problem occurred: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Student Records Management System ---");
        System.out.println("1. Register a New Student");
        System.out.println("2. Update Student Information");
        System.out.println("3. Remove a Student Record");
        System.out.println("4. Find a Student");
        System.out.println("5. Organize Students (Quick Sort)");
        System.out.println("6. Organize Students (Bubble Sort)");
        System.out.println("7. Show All Students");
        System.out.println("8. Add Randomly Generated Students");
        System.out.println("9. Exit the Program");
    }

    private static void addStudent(Scanner scanner) {
        try {
            System.out.print("Provide a unique student ID: ");
            int id = scanner.nextInt();
            if (id < 0) {
                System.out.println("The ID cannot be less than zero. Please try again.");
                return;
            }

            scanner.nextLine(); // Consume newline left by nextInt()
            String name = getValidName(scanner);

            System.out.print("Enter the student's marks: ");
            double marks = scanner.nextDouble();
            if (marks < 0) {
                System.out.println("Marks must be a positive value. Please re-enter.");
                return;
            }

            Student student = new Student(id, name, marks);
            studentStack.push(student);
            System.out.println("Student has been successfully added.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid data detected. Please enter correct values.");
            scanner.next(); // Clear invalid input
        }
    }

    private static void editStudent(Scanner scanner) {
        try {
            System.out.print("Specify the ID of the student to update: ");
            int id = scanner.nextInt();
            if (id < 0) {
                System.out.println("The ID must be a non-negative number. Try again.");
                return;
            }

            scanner.nextLine(); // Consume newline left by nextInt()
            String name = getValidName(scanner);

            System.out.print("Input the updated marks for the student: ");
            double marks = scanner.nextDouble();
            if (marks < 0) {
                System.out.println("Marks cannot be negative. Please input a valid number.");
                return;
            }

            studentStack.editStudent(id, name, marks);
            System.out.println("Student details have been successfully updated.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Input must be in the correct format. Please try again.");
            scanner.next(); // Clear invalid input
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter the ID of the student to remove: ");
        int id = scanner.nextInt();
        studentStack.deleteStudent(id);
        System.out.println("The student record has been deleted.");
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Provide the student ID to locate: ");
        int id = scanner.nextInt();
        Student foundStudent = studentStack.searchStudent(id);
        if (foundStudent != null) {
            System.out.printf("Student Found! ID: %d, Name: %s, Marks: %.2f, Rank: %s%n",
                    foundStudent.getId(), foundStudent.getName(), foundStudent.getMarks(), foundStudent.getRank());
        } else {
            System.out.println("No student with this ID exists in the records.");
        }
    }

    private static void generateRandomStudents(Scanner scanner) {
        System.out.print("How many random students should be created? ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            int id = random.nextInt(1000); // Random ID between 0 and 999
            String name = "AutoStudent" + (i + 1); // Naming convention for generated students
            double marks = 1 + (10 - 1) * random.nextDouble(); // Marks between 1.0 and 10.0
            Student student = new Student(id, name, marks);
            studentStack.push(student);
        }
        System.out.println(count + " random students have been successfully added.");
    }

    private static String getValidName(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("Input the full name of the student: ");
            name = scanner.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                return name;
            } else {
                System.out.println("The name should only contain letters and spaces. Please re-enter.");
            }
        }
    }
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
