package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.*;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;
import java.util.Scanner;

/**
 * Console UI layer displaying menus, reading input, and triggering service methods.
 */
public class MenuUI {
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final Scanner scanner;

    public MenuUI() {
        this.studentService = new StudentService();
        this.courseService = new CourseService();
        this.enrollmentService = new EnrollmentService();
        this.scanner = new Scanner(System.in);
        seedSampleData(); // Pre-populate sample data for immediate testing
    }

    /**
     * Seeds initial sample data.
     */
    private void seedSampleData() {
        try {
            Student s1 = studentService.addStudent("Alice", "Smith", "alice@airtribe.com", "Cohort-2026");
            Student s2 = studentService.addStudent("Bob", "Johnson", "bob@airtribe.com", "Cohort-2026");
            Student s3 = studentService.addStudent("Charlie", "Brown", "charlie@airtribe.com", "Cohort-2025");

            Course c1 = courseService.addCourse("Core Java Fundamentals", "Master OOP, Collections, and Exception Handling in Core Java.", 8);
            Course c2 = courseService.addCourse("System Design & Architecture", "Learn Scalable Systems, Low-Level and High-Level Design.", 12);
            Course c3 = courseService.addCourse("Spring Boot Microservices", "Build REST APIs and Enterprise Microservices.", 10);

            enrollmentService.enrollStudent(s1.getId(), c1.getId(), studentService, courseService);
            enrollmentService.enrollStudent(s2.getId(), c1.getId(), studentService, courseService);
            enrollmentService.enrollStudent(s2.getId(), c2.getId(), studentService, courseService);
        } catch (Exception e) {
            // Ignore error during initial seeding
        }
    }

    /**
     * Starts the main console application loop.
     */
    public void start() {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = InputValidator.readInt(scanner, "Enter option (1-5): ");
            System.out.println();

            switch (choice) {
                case 1:
                    handleStudentMenu();
                    break;
                case 2:
                    handleCourseMenu();
                    break;
                case 3:
                    handleEnrollmentMenu();
                    break;
                case 4:
                    seedSampleData();
                    System.out.println("✅ Sample data refreshed/re-seeded successfully!");
                    break;
                case 5:
                    running = false;
                    System.out.println("==================================================");
                    System.out.println("   Thank you for using LearnTrack. Goodbye! 👋");
                    System.out.println("==================================================");
                    break;
                default:
                    System.out.println("⚠️ Invalid option selected. Please enter a number between 1 and 5.");
            }
            System.out.println();
        }
    }

    private void printMainMenu() {
        System.out.println("==================================================");
        System.out.println("         🎓 LEARNTRACK CONSOLE DASHBOARD          ");
        System.out.println("==================================================");
        System.out.println(" 1. 👨‍🎓 Student Management");
        System.out.println(" 2. 📚 Course Management");
        System.out.println(" 3. 📝 Enrollment Management");
        System.out.println(" 4. 🔄 Seed Sample Data");
        System.out.println(" 5. 🚪 Exit Application");
        System.out.println("==================================================");
    }

    // ==================== STUDENT MANAGEMENT ====================
    private void handleStudentMenu() {
        boolean inSubMenu = true;
        while (inSubMenu) {
            System.out.println("\n--- 👨‍🎓 STUDENT MANAGEMENT ---");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Deactivate Student");
            System.out.println("5. Reactivate Student");
            System.out.println("6. Update Student Info");
            System.out.println("7. ⬅️ Return to Main Menu");

            int choice = InputValidator.readInt(scanner, "Select Student option (1-7): ");
            System.out.println();

            try {
                switch (choice) {
                    case 1:
                        String fName = InputValidator.readNonEmptyString(scanner, "Enter First Name: ");
                        String lName = InputValidator.readNonEmptyString(scanner, "Enter Last Name: ");
                        String email = InputValidator.readNonEmptyString(scanner, "Enter Email: ");
                        String batch = InputValidator.readNonEmptyString(scanner, "Enter Batch: ");

                        Student newStudent = studentService.addStudent(fName, lName, email, batch);
                        System.out.println("✅ Student created successfully!");
                        System.out.println("   " + newStudent.getDisplayName());
                        break;

                    case 2:
                        List<Student> students = studentService.getAllStudents();
                        if (students.isEmpty()) {
                            System.out.println("⚠️ No students found in system.");
                        } else {
                            System.out.println("📋 ALL REGISTERED STUDENTS (" + students.size() + "):");
                            for (Student s : students) {
                                System.out.println("   - " + s.getDisplayName());
                            }
                        }
                        break;

                    case 3:
                        int searchId = InputValidator.readInt(scanner, "Enter Student ID to search: ");
                        Student found = studentService.getStudentById(searchId);
                        System.out.println("✅ Student Found:");
                        System.out.println("   " + found.getDisplayName());
                        break;

                    case 4:
                        int deactId = InputValidator.readInt(scanner, "Enter Student ID to deactivate: ");
                        studentService.deactivateStudent(deactId);
                        System.out.println("✅ Student ID " + deactId + " has been deactivated successfully.");
                        break;

                    case 5:
                        int reactId = InputValidator.readInt(scanner, "Enter Student ID to reactivate: ");
                        studentService.activateStudent(reactId);
                        System.out.println("✅ Student ID " + reactId + " has been reactivated successfully.");
                        break;

                    case 6:
                        int upId = InputValidator.readInt(scanner, "Enter Student ID to update: ");
                        Student current = studentService.getStudentById(upId);
                        System.out.println("Current details: " + current.getDisplayName());

                        String upFName = InputValidator.readNonEmptyString(scanner, "Enter New First Name: ");
                        String upLName = InputValidator.readNonEmptyString(scanner, "Enter New Last Name: ");
                        String upEmail = InputValidator.readNonEmptyString(scanner, "Enter New Email: ");
                        String upBatch = InputValidator.readNonEmptyString(scanner, "Enter New Batch: ");

                        studentService.updateStudent(upId, upFName, upLName, upEmail, upBatch);
                        System.out.println("✅ Student updated successfully!");
                        break;

                    case 7:
                        inSubMenu = false;
                        break;

                    default:
                        System.out.println("⚠️ Invalid sub-menu choice.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    // ==================== COURSE MANAGEMENT ====================
    private void handleCourseMenu() {
        boolean inSubMenu = true;
        while (inSubMenu) {
            System.out.println("\n--- 📚 COURSE MANAGEMENT ---");
            System.out.println("1. Add New Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course by ID");
            System.out.println("4. Toggle Course Active/Inactive Status");
            System.out.println("5. ⬅️ Return to Main Menu");

            int choice = InputValidator.readInt(scanner, "Select Course option (1-5): ");
            System.out.println();

            try {
                switch (choice) {
                    case 1:
                        String name = InputValidator.readNonEmptyString(scanner, "Enter Course Name: ");
                        String desc = InputValidator.readNonEmptyString(scanner, "Enter Description: ");
                        int duration = InputValidator.readInt(scanner, "Enter Duration (in weeks): ");

                        Course newCourse = courseService.addCourse(name, desc, duration);
                        System.out.println("✅ Course created successfully!");
                        System.out.println("   " + newCourse.getFormattedDetails());
                        break;

                    case 2:
                        List<Course> courses = courseService.getAllCourses();
                        if (courses.isEmpty()) {
                            System.out.println("⚠️ No courses available.");
                        } else {
                            System.out.println("📋 ALL COURSES (" + courses.size() + "):");
                            for (Course c : courses) {
                                System.out.println(c.getFormattedDetails());
                                System.out.println("--------------------------------------------------");
                            }
                        }
                        break;

                    case 3:
                        int courseId = InputValidator.readInt(scanner, "Enter Course ID: ");
                        Course found = courseService.getCourseById(courseId);
                        System.out.println("✅ Course Details:");
                        System.out.println(found.getFormattedDetails());
                        break;

                    case 4:
                        int toggleId = InputValidator.readInt(scanner, "Enter Course ID to toggle status: ");
                        courseService.toggleCourseStatus(toggleId);
                        Course toggled = courseService.getCourseById(toggleId);
                        System.out.println("✅ Course ID " + toggleId + " status toggled! New Status: " + (toggled.isActive() ? "ACTIVE" : "INACTIVE"));
                        break;

                    case 5:
                        inSubMenu = false;
                        break;

                    default:
                        System.out.println("⚠️ Invalid option.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    // ==================== ENROLLMENT MANAGEMENT ====================
    private void handleEnrollmentMenu() {
        boolean inSubMenu = true;
        while (inSubMenu) {
            System.out.println("\n--- 📝 ENROLLMENT MANAGEMENT ---");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. View Enrollments for a Student");
            System.out.println("3. Update Enrollment Status (Active / Completed / Cancelled)");
            System.out.println("4. View All System Enrollments");
            System.out.println("5. ⬅️ Return to Main Menu");

            int choice = InputValidator.readInt(scanner, "Select Enrollment option (1-5): ");
            System.out.println();

            try {
                switch (choice) {
                    case 1:
                        int studentId = InputValidator.readInt(scanner, "Enter Student ID to enroll: ");
                        int courseId = InputValidator.readInt(scanner, "Enter Course ID to enroll in: ");

                        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId, studentService, courseService);
                        System.out.println("✅ Enrollment successful!");
                        System.out.println("   " + enrollment);
                        break;

                    case 2:
                        int sId = InputValidator.readInt(scanner, "Enter Student ID: ");
                        Student student = studentService.getStudentById(sId);
                        List<Enrollment> list = enrollmentService.getEnrollmentsByStudent(sId);
                        System.out.println("📋 Enrollments for " + student.getFullName() + " (ID: " + sId + "):");
                        if (list.isEmpty()) {
                            System.out.println("   No enrollments found for this student.");
                        } else {
                            for (Enrollment e : list) {
                                try {
                                    Course c = courseService.getCourseById(e.getCourseId());
                                    System.out.println("   - Enrollment ID: " + e.getId() + " | Course: " + c.getCourseName() + " | Date: " + e.getEnrollmentDate() + " | Status: " + e.getStatus());
                                } catch (EntityNotFoundException ex) {
                                    System.out.println("   - " + e);
                                }
                            }
                        }
                        break;

                    case 3:
                        int enrId = InputValidator.readInt(scanner, "Enter Enrollment ID: ");
                        System.out.println("Select New Status:");
                        System.out.println("1. ACTIVE");
                        System.out.println("2. COMPLETED");
                        System.out.println("3. CANCELLED");
                        int stChoice = InputValidator.readInt(scanner, "Enter status option (1-3): ");

                        EnrollmentStatus newStatus;
                        if (stChoice == 1) newStatus = EnrollmentStatus.ACTIVE;
                        else if (stChoice == 2) newStatus = EnrollmentStatus.COMPLETED;
                        else if (stChoice == 3) newStatus = EnrollmentStatus.CANCELLED;
                        else {
                            System.out.println("⚠️ Invalid status choice. Aborting status update.");
                            break;
                        }

                        enrollmentService.updateEnrollmentStatus(enrId, newStatus);
                        System.out.println("✅ Enrollment ID " + enrId + " updated to status: " + newStatus);
                        break;

                    case 4:
                        List<Enrollment> all = enrollmentService.getAllEnrollments();
                        if (all.isEmpty()) {
                            System.out.println("⚠️ No enrollments in system.");
                        } else {
                            System.out.println("📋 ALL ENROLLMENTS IN SYSTEM (" + all.size() + "):");
                            for (Enrollment e : all) {
                                System.out.println("   - " + e);
                            }
                        }
                        break;

                    case 5:
                        inSubMenu = false;
                        break;

                    default:
                        System.out.println("⚠️ Invalid choice.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}
