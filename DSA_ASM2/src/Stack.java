import java.util.Arrays;

public class Stack {
    private static Student[] students;
    private static int top;
    private static int size;

    public Stack(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("The stack size must be a positive number.");
        }
        this.size = size;
        students = new Student[size];
        top = -1;
    }

    public static void push(Student student) {
        if (top >= size - 1) {
            System.out.println("Unable to add more students. The stack is at full capacity.");
            return;
        }
        students[++top] = student;
        System.out.println("The student has been successfully added to the stack.");
    }

    public static void editStudent(int id, String name, double marks) {
        if (id <= 0) {
            System.out.println("Error: Student ID must be greater than zero.");
            return;
        }

        for (int i = 0; i <= top; i++) {
            if (students[i].getId() == id) {
                try {
                    students[i] = new Student(id, name, marks);
                    System.out.println("Student details have been successfully updated.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Failed to update student: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("No student found with the ID " + id + ".");
    }

    public static void deleteStudent(int id) {
        if (id <= 0) {
            System.out.println("Error: The student ID must be a positive number.");
            return;
        }

        for (int i = 0; i <= top; i++) {
            if (students[i].getId() == id) {
                for (int j = i; j < top; j++) {
                    students[j] = students[j + 1]; // Shift elements
                }
                students[top--] = null; // Clear the last position
                System.out.println("Student has been successfully removed.");
                return;
            }
        }
        System.out.println("No record found for the student ID " + id + ".");
    }

    public static Student searchStudent(int id) {
        if (id <= 0) {
            System.out.println("Invalid input: The ID must be a positive integer.");
            return null;
        }

        for (int i = 0; i <= top; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }
        System.out.println("Student with the ID " + id + " could not be located.");
        return null;
    }

    public void sortStudentsQuick() {
        long startTime = System.nanoTime();
        quickSort(students, 0, top);
        long endTime = System.nanoTime();
        long durationNanoseconds = (endTime - startTime);
        System.out.println("Students have been sorted by ID using Quick Sort in " + durationNanoseconds + " nanoseconds.");
    }

    private void quickSort(Student[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }


    private int partition(Student[] array, int low, int high) {
        int pivot = array[high].getId();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j].getId() <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(Student[] array, int i, int j) {
        Student temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void sortStudentsBubble() {
        long startTime = System.nanoTime();
        bubbleSort();
        long endTime = System.nanoTime();
        long durationNanoseconds = (endTime - startTime);
        System.out.println("Students have been sorted by ID using Bubble Sort in " + durationNanoseconds + " nanoseconds.");
    }

    private void bubbleSort() {
        int n = top + 1;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students[j].getId() > students[j + 1].getId()) {
                    swap(students, j, j + 1);
                }
            }
        }
    }

    public void displayStudents() {
        if (top == -1) {
            System.out.println("The stack is empty. No student records to show.");
            return;
        }
        for (int i = 0; i <= top; i++) {
            System.out.printf("Student ID: %d | Name: %s | Marks: %.2f | Rank: %s%n",
                    students[i].getId(), students[i].getName(), students[i].getMarks(), students[i].getRank());
        }
    }
}
