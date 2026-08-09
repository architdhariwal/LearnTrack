package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.EnrollmentStatus;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service managing Student-Course Enrollments using ArrayList.
 */
public class EnrollmentService {
    private final List<Enrollment> enrollments;

    public EnrollmentService() {
        this.enrollments = new ArrayList<>();
    }

    /**
     * Enrolls a student in a course after validating both entities exist and are active.
     */
    public Enrollment enrollStudent(int studentId, int courseId, StudentService studentService, CourseService courseService)
            throws EntityNotFoundException, InvalidInputException {
        
        Student student = studentService.getStudentById(studentId);
        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll student: Student ID " + studentId + " is currently INACTIVE.");
        }

        Course course = courseService.getCourseById(courseId);
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll student: Course ID " + courseId + " is currently INACTIVE.");
        }

        // Check for duplicate active enrollment
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId && e.getCourseId() == courseId && e.getStatus() == EnrollmentStatus.ACTIVE) {
                throw new InvalidInputException("Student ID " + studentId + " is already actively enrolled in Course ID " + courseId + ".");
            }
        }

        int id = IdGenerator.getNextEnrollmentId();
        String today = LocalDate.now().toString();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, today, EnrollmentStatus.ACTIVE);
        enrollments.add(enrollment);
        return enrollment;
    }

    /**
     * Retrieves all enrollments for a specific student.
     */
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }

    /**
     * Finds enrollment by ID.
     */
    public Enrollment getEnrollmentById(int enrollmentId) throws EntityNotFoundException {
        for (Enrollment e : enrollments) {
            if (e.getId() == enrollmentId) {
                return e;
            }
        }
        throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " was not found.");
    }

    /**
     * Updates status of an enrollment (ACTIVE, COMPLETED, CANCELLED).
     */
    public void updateEnrollmentStatus(int enrollmentId, EnrollmentStatus newStatus) throws EntityNotFoundException {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        enrollment.setStatus(newStatus);
    }

    /**
     * Returns list of all enrollments.
     */
    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }
}
