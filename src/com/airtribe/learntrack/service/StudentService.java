package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Service managing Student in-memory data using ArrayList.
 */
public class StudentService {
    private final List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
    }

    /**
     * Adds an existing student object to the system.
     */
    public void addStudent(Student student) throws InvalidInputException {
        if (student == null) {
            throw new InvalidInputException("Student object cannot be null.");
        }
        students.add(student);
    }

    /**
     * Method Overloading: Create and add a new student using field parameters.
     */
    public Student addStudent(String firstName, String lastName, String email, String batch) throws InvalidInputException {
        if (firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidInputException("First name and Last name are required.");
        }
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName.trim(), lastName.trim(), email.trim(), batch.trim());
        students.add(student);
        return student;
    }

    /**
     * Retrieves student by ID. Throws EntityNotFoundException if missing.
     */
    public Student getStudentById(int id) throws EntityNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student with ID " + id + " was not found.");
    }

    /**
     * Returns list of all students.
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Return copy to prevent direct collection modification
    }

    /**
     * Deactivates student (soft delete: sets active = false).
     */
    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setActive(false);
    }

    /**
     * Reactivates a student.
     */
    public void activateStudent(int id) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setActive(true);
    }

    /**
     * Updates student details.
     */
    public void updateStudent(int id, String firstName, String lastName, String email, String batch) throws EntityNotFoundException {
        Student student = getStudentById(id);
        if (firstName != null && !firstName.trim().isEmpty()) student.setFirstName(firstName.trim());
        if (lastName != null && !lastName.trim().isEmpty()) student.setLastName(lastName.trim());
        if (email != null && !email.trim().isEmpty()) student.setEmail(email.trim());
        if (batch != null && !batch.trim().isEmpty()) student.setBatch(batch.trim());
    }
}
