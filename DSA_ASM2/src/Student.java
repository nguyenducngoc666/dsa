public class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID. Must be a positive number.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Name. Name cannot be empty.");
        }
        if (marks < 0 || marks > 10) {
            throw new IllegalArgumentException("Invalid Marks. Must be between 0 and 10.");
        }
        this.id = id;
        this.name = name.trim();
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public String getRank() {
        if (marks < 5.0) {
            return "Fail";
        } else if (marks < 6.5) {
            return "Medium";
        } else if (marks < 7.5) {
            return "Good";
        } else if (marks < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Ranking: " + getRank();
    }
}
